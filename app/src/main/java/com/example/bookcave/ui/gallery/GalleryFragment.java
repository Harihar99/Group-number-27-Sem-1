package com.example.bookcave.ui.gallery;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bookcave.R;
import com.example.bookcave.extras.ABRecyclerViewAdapter;
import com.example.bookcave.extras.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class GalleryFragment extends Fragment {

    private ImageButton search_book_btn;
    private EditText search_book;
    private RecyclerView result_list;
    private ProgressBar loading_indicator;
    private TextView error_message;
    private ArrayList<Book> mBooks;
    private ABRecyclerViewAdapter mAdapter;
    private RequestQueue mRequestQueue;
    String user;
    private static  final  String BASE_URL="https://www.googleapis.com/books/v1/volumes?q=";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        search_book_btn=root.findViewById(R.id.search_book_btn);
        search_book=root.findViewById(R.id.search_book);
        result_list=root.findViewById(R.id.result_list);
        loading_indicator=root.findViewById(R.id.loading_indicator);
        error_message= root.findViewById(R.id.message_display);
        mBooks = new ArrayList<>();
        result_list.setHasFixedSize(true);
        result_list.setLayoutManager(new LinearLayoutManager(getActivity()));

        mBooks = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getActivity());

        search_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBooks.clear();
                search();
            }
        });

        return root;
    }

    private void parseJson(String key) {

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, key.toString(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String title ="";
                        String author ="";
                        String publishedDate = "NoT Available";
                        String description = "No Description";
                        int pageCount = 1000;
                        String categories = "No categories Available ";
                        String buy ="";

                        String price = "NOT_FOR_SALE";
                        try {
                            JSONArray items = response.getJSONArray("items");

                            for (int i = 0 ; i< items.length() ;i++){
                                JSONObject item = items.getJSONObject(i);
                                JSONObject volumeInfo = item.getJSONObject("volumeInfo");

                                try{
                                    title = volumeInfo.getString("title");

                                    JSONArray authors = volumeInfo.getJSONArray("authors");
                                    if(authors.length() == 1){
                                        author = authors.getString(0);
                                    }else {
                                        author = authors.getString(0) + "|" +authors.getString(1);
                                    }

                                    publishedDate = volumeInfo.getString("publishedDate");
                                    pageCount = volumeInfo.getInt("pageCount");
                                    JSONObject saleInfo = item.getJSONObject("saleInfo");
                                    JSONObject listPrice = saleInfo.getJSONObject("listPrice");
                                    price = listPrice.getString("amount") + " " +listPrice.getString("currencyCode");
                                    description = volumeInfo.getString("description");
                                    buy = saleInfo.getString("buyLink");
                                    categories = volumeInfo.getJSONArray("categories").getString(0);

                                }catch (Exception e){
                                        Log.d("TAG", e.toString());
                                }

                                String thumbnail = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");
                                String previewLink = volumeInfo.getString("previewLink");
                                String url = volumeInfo.getString("infoLink");

                                mBooks.add(new Book(title , author , publishedDate , description ,categories
                                        ,thumbnail,buy,previewLink,price,pageCount,url));


//write adapter code from mwsc

                                mAdapter = new ABRecyclerViewAdapter(getActivity() , mBooks);
                                result_list.setAdapter(mAdapter);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("TAG" , e.toString());

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    private boolean Read_network_state(Context context)
    {    boolean is_connected;
        ConnectivityManager cm =(ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        is_connected=info!=null&&info.isConnectedOrConnecting();
        return is_connected;
    }

    private void search() {
        String search_query = search_book.getText().toString();

        boolean is_connected = Read_network_state(getActivity());
        if(!is_connected)
        {
            error_message.setText(R.string.Failed_to_Load_data);
            result_list.setVisibility(View.INVISIBLE);
            error_message.setVisibility(View.VISIBLE);
            return;
        }

        if(search_query.equals(""))
        {
            Toast.makeText(getActivity(),"Please enter something to search",Toast.LENGTH_SHORT).show();
            return;
        }
        String final_query=search_query.replace(" ","+");
        Uri uri=Uri.parse(BASE_URL+final_query);
        Uri.Builder buider = uri.buildUpon();

        parseJson(buider.toString());

    }
}

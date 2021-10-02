package com.example.bookcave.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bookcave.R;
import com.example.bookcave.extras.Order;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import org.jetbrains.annotations.NotNull;

public class OrderHistoryC extends AppCompatActivity {
    private RecyclerView recyler_order_history_c;
    private FirebaseFirestore firebaseFirestore;
    private FirestoreRecyclerAdapter adapter;
    FirebaseAuth firebaseAuth;
    private Query query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_c);

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefreshOrderHis);
        recyler_order_history_c = findViewById(R.id.recyler_order_history_c);

        showOrderHistory();
        recyler_order_history_c.setHasFixedSize(true);
        recyler_order_history_c.setLayoutManager(new LinearLayoutManager(this));
        recyler_order_history_c.setAdapter(adapter);
        //End of adapter code

        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showOrderHistory();
                pullToRefresh.setRefreshing(false);
            }
        });
    }

    public void showOrderHistory() {
        query = firebaseFirestore.collection("Orders").whereEqualTo("customerid", firebaseAuth.getCurrentUser().getUid());

        FirestoreRecyclerOptions<Order> options = new FirestoreRecyclerOptions.Builder<Order>()
                .setQuery(query, Order.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Order, OrdersViewHolder>(options) {
            @Override
            public OrdersViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.row_order_cus_list, parent, false);

                return new OrdersViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NotNull OrdersViewHolder viewHolder, int position, @NotNull final Order model) {
                //get id and query to set book name and author
                viewHolder.row_bprice.setText(model.getPrice());
                viewHolder.row_updatedon.setText(model.getUpdatedat());
            }
        };
    }
        public class OrdersViewHolder extends RecyclerView.ViewHolder {
            View mView;
            LinearLayout container; //row_order_cus_list
            TextView row_bname,row_bauthor,row_bprice,row_bstatus,row_updatedon;

            OrdersViewHolder(@NonNull View itemView) {
                super(itemView);
                mView = itemView;
                container=mView.findViewById(R.id.container);
                //row_bname=mView.findViewById(R.id.row_bname);
                //row_bauthor=mView.findViewById(R.id.row_bauthor);
                row_bprice=mView.findViewById(R.id.row_bprice);
                //row_bstatus=mView.findViewById(R.id.row_bstatus);
                row_updatedon=mView.findViewById(R.id.row_updatedon);

            }
        }

        @Override
        public void onStart(){
        super.onStart();
        adapter.startListening();
        }
        @Override
        public void onStop(){
        super.onStop();
        adapter.stopListening();
        }
        @Override
        public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
        }
        @Override
        public void onPause(){
        super.onPause();
        adapter.stopListening();
        }
}

package com.example.bookcave.extras;

public class OrderedBook {

    // unique IDs
    private String bookid;
    private String sellerid;
    private String customerid;

    //Book details
    //String
    private String title;
    private String author;
    private String category;
    private String thumbnail;
    //Integers
    private int sellingprice;
    private int deliverycharges;
    private int rentingprice;
    private int thbook;

    //Seller details
    private String shop;
    private String phno_company;

    //Customer details i.e. ordering details
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String pincode;

    //additional details
    private String createdon;
    private String status;
    private String updatedat;
    private int iscancelled;

    public OrderedBook(){}

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(int sellingprice) {
        this.sellingprice = sellingprice;
    }

    public int getDeliverycharges() {
        return deliverycharges;
    }

    public void setDeliverycharges(int deliverycharges) {
        this.deliverycharges = deliverycharges;
    }

    public int getRentingprice() {
        return rentingprice;
    }

    public void setRentingprice(int rentingprice) {
        this.rentingprice = rentingprice;
    }

    public int getThbook() {
        return thbook;
    }

    public void setThbook(int thbook) {
        this.thbook = thbook;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getPhno_company() {
        return phno_company;
    }

    public void setPhno_company(String phno_company) {
        this.phno_company = phno_company;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedat() {
        return updatedat;
    }

    public void setUpdatedat(String updatedat) {
        this.updatedat = updatedat;
    }

    public int getIscancelled() {
        return iscancelled;
    }

    public void setIscancelled(int iscancelled) {
        this.iscancelled = iscancelled;
    }
}

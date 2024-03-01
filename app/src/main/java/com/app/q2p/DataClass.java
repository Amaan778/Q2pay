package com.app.q2p;

public class DataClass {
    private String id;
    private String title;
    private String img;

    private String price;
    private String brand;

    public DataClass(String id, String title, String img, String price, String brand) {
        this.id = id;
        this.title = title;
        this.img=img;
        this.price=price;
        this.brand=brand;
    }

    public String getPrice(){
        return price;
    }

    public String getBrand(){
        return brand;
    }

    public String getImg(){
        return img;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}

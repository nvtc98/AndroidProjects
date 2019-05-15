package com.example.baitap3;

public class Dish {
    private int ID;
    private String DishName;
    private String Describe;
    private String LinkImage;
    private String LinkDetails;
    private int Rating;

    public Dish(){}
    public Dish(int ID, String DishName,String Describe,String LinkImage, String LinkDetails, int Rating){
        this.ID = ID;
        this.DishName = DishName;
        this.Describe = Describe;
        this.LinkImage = LinkImage;
        this.LinkDetails = LinkDetails;
        this.Rating = Rating;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDishName() {
        return DishName;
    }

    public void setDishName(String dishName) {
        DishName = dishName;
    }

    public String getDescribe() {
        return Describe;
    }

    public void setDescribe(String describe) {
        Describe = describe;
    }

    public String getLinkImage() {
        return LinkImage;
    }

    public void setLinkImage(String dishImage) {
        LinkImage = dishImage;
    }

    public String getLinkDetails() {
        return LinkDetails;
    }

    public void setLinkDetails(String linkDetails) {
        LinkDetails = linkDetails;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int rating) {
        Rating = rating;
    }
}

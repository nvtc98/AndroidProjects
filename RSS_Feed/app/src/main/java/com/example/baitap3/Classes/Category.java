package com.example.baitap3.Classes;

import java.util.List;

public class Category {
    private int ID;
    private String CategoryName;
    private List<Dish> Dishs;

    public Category(int ID, String CategoryName, List<Dish> Dishs){
        this.ID = ID;
        this.CategoryName = CategoryName;
        this.Dishs = Dishs;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public List<Dish> getDishs() {
        return Dishs;
    }

    public void setDishs(List<Dish> dishs) {
        Dishs = dishs;
    }
}

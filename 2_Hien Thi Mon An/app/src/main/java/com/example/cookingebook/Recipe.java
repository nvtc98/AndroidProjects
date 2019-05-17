package com.example.cookingebook;

import java.io.Serializable;

public class Recipe implements Serializable {

  private int Prep, Inactive, Cook, Yield, photoId;
  private String Name, Desc;
  private String[] Ingredients;
  private String[] Steps;

  public Recipe() {
  }

  public Recipe(String name, String desc, String[] ingredients, String[] steps, int yield, int prep,
      int inactive, int cook, int photoID) {
    Prep = prep;
    Inactive = inactive;
    Cook = cook;
    Yield = yield;
    Name = name;
    Desc = desc;
    Ingredients = ingredients;
    Steps = steps;
    photoId = photoID;
  }

  public int getPrep() {
    return Prep;
  }

  public void setPrep(int prep) {
    Prep = prep;
  }

  public int getInactive() {
    return Inactive;
  }

  public void setInactive(int inactive) {
    Inactive = inactive;
  }

  public int getCook() {
    return Cook;
  }

  public void setCook(int cook) {
    Cook = cook;
  }

  public int getYield() {
    return Yield;
  }

  public void setYield(int yield) {
    Yield = yield;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getDesc() {
    return Desc;
  }

  public void setDesc(String desc) {
    Desc = desc;
  }

  public String getIngredients() {
    String Ingredient = "Thành phần:\n";
    int i = 1;
    for (String I : Ingredients) {
      Ingredient += +i + "> " + I + "\n";
      i++;
    }
    return Ingredient;
  }

  public void setIngredients(String[] ingredients) {
    Ingredients = ingredients;
  }

  public int getPhotoId() {
    return photoId;
  }

  public void setPhotoId(int photoId) {
    this.photoId = photoId;
  }

  public String getSteps() {
    String Step = "Cách thực hiện: \n";
    int i = 1;
    for (String S : Steps) {
      Step += "Bước: " + i + ": " + S + "\n";
      i++;
    }
    return Step;
  }

  public void setSteps(String[] steps) {
    Steps = steps;
  }

  public int getTotalTime() {
    return getCook() + getInactive() + getPrep();
  }
}


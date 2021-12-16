package com.example.recipeproject.Model;

public class RecipeData {

    String title;
    int duration;
    String cuisine;
    String img;
    String ingredients;


    public RecipeData(String title, int duration, String cuisine, String img,String ingredients){
        this.title = title;
        this.duration = duration;
        this.cuisine = cuisine;
        this.img = img;
        this.ingredients = ingredients;

    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getImg() { return img; }

    public String getIngredients() {
        return ingredients;
    }
}

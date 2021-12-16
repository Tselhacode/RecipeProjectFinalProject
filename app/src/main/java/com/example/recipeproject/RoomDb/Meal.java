package com.example.recipeproject.RoomDb;

import androidx.room.PrimaryKey;

public class Meal {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String mealName;// Toronto , On,Canada


    Meal(){}
    public Meal(String city){
        this.mealName = city;
    }

    public int getId() {
        return id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void getMealName(String mealName) {
        this.mealName = mealName;
    }
}

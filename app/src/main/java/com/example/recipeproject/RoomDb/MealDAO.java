package com.example.recipeproject.RoomDb;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.recipeproject.RoomDb.Meal;

import java.util.List;

public abstract class MealDAO {
    @Insert
    abstract void addNewMeal(Meal m);
//
//    @Insert
//    void addTwoCities(City c1, City c2);
//
//    @Insert
//    void addAllCities(List<City> list);


    @Delete
    abstract void deleteMeal(Meal m);

    @Query("SELECT * FROM Meal")
    abstract List<Meal> getAllMeals();
}

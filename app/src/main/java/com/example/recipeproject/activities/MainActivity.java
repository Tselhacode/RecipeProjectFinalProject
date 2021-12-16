package com.example.recipeproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.recipeproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView breakfast;
    ImageView brunch;
    ImageView lunch;
    ImageView dinner;
    ImageView vegetarian;
    ImageView nonVeg;
    ImageView pesca;
    ImageView keto;
    ImageView vegan;
    ImageView glutenFree;
    ImageView nutFree;
    ImageView eggFree;
    ImageView dairyFree;
    Button recipeButton;

    String meal;
    String diet;
    String intolerance;

    private static String mealString;
    private static String dietString;
    private static String intoleranceString;

    private View v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        breakfast = findViewById(R.id.breakfast);
        breakfast.setOnClickListener(this);
        brunch = findViewById(R.id.brunch);
        brunch.setOnClickListener(this);
        lunch = findViewById(R.id.lunch);
        lunch.setOnClickListener(this);
        dinner = findViewById(R.id.dinner);
        dinner.setOnClickListener(this);
        vegetarian = findViewById(R.id.Vegetarian);
        vegetarian.setOnClickListener(this);
        nonVeg = findViewById(R.id.NonVeg);
        nonVeg.setOnClickListener(this);
        pesca = findViewById(R.id.Pescatarian);
        pesca.setOnClickListener(this);
        keto = findViewById(R.id.Keto);
        keto.setOnClickListener(this);
        vegan = findViewById(R.id.Vegan);
        vegan.setOnClickListener(this);
        glutenFree = findViewById(R.id.gluten);
        glutenFree.setOnClickListener(this);
        nutFree = findViewById(R.id.nuts);
        nutFree.setOnClickListener(this);
        eggFree = findViewById(R.id.egg);
        eggFree.setOnClickListener(this);
        dairyFree = findViewById(R.id.dairy);
        dairyFree.setOnClickListener(this);
        recipeButton = findViewById(R.id.recipeButton);
        recipeButton.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        this.v = v;
        if ((v.getId() == R.id.breakfast) || (v.getId() == R.id.brunch) || (v.getId() == R.id.lunch) || (v.getId() == R.id.dinner)) {
            switch (v.getId()) {
                case R.id.breakfast:
                    meal = "breakfast";
                    Toast.makeText(this, "you selected " + meal + "",
                            Toast.LENGTH_LONG).show();
                    break;
                case R.id.brunch:
                    meal = "brunch";
                    Toast.makeText(this, "you selected " + meal + "",
                            Toast.LENGTH_LONG).show();
                    break;
                case R.id.lunch:
                    meal = "lunch";
                    Toast.makeText(this, "you selected " + meal + "",
                            Toast.LENGTH_LONG).show();
                    break;
                case R.id.dinner:
                    meal = "dinner";
                    Toast.makeText(this, "you selected " + meal + "",
                            Toast.LENGTH_LONG).show();
                    break;

            }
            Log.d("meal",meal);
        }

        if ((v.getId() == R.id.Vegetarian) || (v.getId() == R.id.NonVeg) || (v.getId() == R.id.Pescatarian) || (v.getId() == R.id.Keto) || (v.getId() == R.id.Vegan)) {
            switch (v.getId()) {
                case R.id.Vegetarian:
                    diet = "Vegetarian";
                    Toast.makeText(this, "you selected " + diet + "",
                            Toast.LENGTH_LONG).show();
                    break;
                case R.id.NonVeg:
                    diet = "NonVeg";
                    Toast.makeText(this, "you selected " + diet + "",
                            Toast.LENGTH_LONG).show();
                    break;
                case R.id.Pescatarian:
                    diet = "Pescatarian";
                    Toast.makeText(this, "you selected " + diet + "",
                            Toast.LENGTH_LONG).show();
                    break;
                case R.id.Vegan:
                    diet = "Vegan";
                    Toast.makeText(this, "you selected " + diet + "",
                            Toast.LENGTH_LONG).show();
                    break;
            }
            Log.d("diet",diet);
        }

        if ((v.getId() == R.id.gluten) || (v.getId() == R.id.dairy) || (v.getId() == R.id.nuts) || (v.getId() == R.id.egg)) {

            switch (v.getId()) {
                case R.id.gluten:
                    intolerance = "GlutenFree";
                    Toast.makeText(this, "you selected " + intolerance + "",
                            Toast.LENGTH_LONG).show();
                    break;
                case R.id.dairy:
                    intolerance = "DairyFree";
                    Toast.makeText(this, "you selected " + intolerance + "",
                            Toast.LENGTH_LONG).show();
                    break;
                case R.id.nuts:
                    intolerance = "NutFree";
                    Toast.makeText(this, "you selected " + intolerance + "",
                            Toast.LENGTH_LONG).show();
                    break;
                case R.id.egg:
                    intolerance = "EggFree";
                    Toast.makeText(this, "you selected " + intolerance + "",
                            Toast.LENGTH_LONG).show();
                    break;
            }
            Log.d("intolerance",intolerance);
        }
        //NUM 1 SEND THE STRINGS TO RECYCLERVIEW
        if (v == recipeButton){
            mealString = meal;
            dietString = diet;
            intoleranceString = intolerance;
            Toast.makeText(this, "You selected " + meal + " and " + diet + " and " + intolerance + "",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, RecyclerActivity.class);
            intent.putExtra("mealString",mealString);
            intent.putExtra("dietString",dietString);
            intent.putExtra("intolerance",intoleranceString);
            startActivity(intent);
        }
    }
}

//the toast takes a long time to dismiss
//it takes a a long time for RecyclerView to populate

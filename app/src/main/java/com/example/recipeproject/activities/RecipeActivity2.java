package com.example.recipeproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.recipeproject.R;

public class RecipeActivity2 extends AppCompatActivity {
    TextView mealName;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        System.out.println("28.RecipeActivity opened");
        mealName = (TextView) findViewById(R.id.name);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        String recipeTitle = intent.getStringExtra("title");
        System.out.println("29.title intent received in RecipeActivity" + recipeTitle);
        String recipeImage = intent.getStringExtra("image");
        System.out.println("30.image intent received in RecipeActivity");
        String ingredientsList = intent.getStringExtra("ingredients");
        System.out.println("31. displaying in RecipeActivity");



        mealName.setText(recipeTitle);
        Glide.with(this).load(recipeImage).into(imageView);
        //use support fragment manager
        updateFragment(ingredientsList);

}

    private void updateFragment(String ingredients) {

        FragmentManager fragmentManager = getSupportFragmentManager();//Fragment manager - which fragment to call, what fragment to display
        fragmentManager.findFragmentById(R.id.frameLayout);//connect it with the layout
        RecipeFragment questionFragment = RecipeFragment.newInstance(ingredients);//putting the question and color in the fragment
        fragmentManager.beginTransaction().replace(R.id.frameLayout, questionFragment).commit();//start activity
    }

}


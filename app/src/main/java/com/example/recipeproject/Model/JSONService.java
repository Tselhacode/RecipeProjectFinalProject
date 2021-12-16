package com.example.recipeproject.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONService {

    //NUM8 PARSING JSON AND GETTING THE REQURIED FIELDS
    String collectionCuisines = "";
    int duration;
    String title;
    String cuisine;
    String img;
    String name;
    String collectionIngredients = "";

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }
    public ArrayList<RecipeData> parseRecipeAPIData(String jsonRecipeString) throws JSONException {
        System.out.println("12.in JSONService parse method in JSONService");
        ArrayList<RecipeData> recipeData = new ArrayList<RecipeData>(0);
        JSONObject jsonObject = new JSONObject(jsonRecipeString);
        JSONArray resultArray = jsonObject.getJSONArray("results");
        for (int i = 0; i < resultArray.length(); i++) {
            JSONObject resultObject = resultArray.getJSONObject(i);
            title = toTitleCase(resultObject.getString("title").toLowerCase());
            duration = resultObject.getInt("readyInMinutes");
            img = resultObject.getString("image");
            JSONArray cuisineArray = resultObject.getJSONArray("cuisines");
            if (cuisineArray.length() == 0) {
                cuisine = "";
            } else {
                for (int j = 0; j < cuisineArray.length(); j++) {
                    cuisine = cuisineArray.getString(j);
                }
            }
            collectionCuisines += (cuisine + " ");
            JSONArray ingredientsArray = resultObject.getJSONArray("analyzedInstructions");
            JSONObject ingredientsObject = ingredientsArray.getJSONObject(0);
            JSONArray stepsArray = ingredientsObject.getJSONArray("steps");
            for (int k = 0; k < stepsArray.length(); k++) {
                JSONObject stepsObject = stepsArray.getJSONObject(k);
                JSONArray ingredientArray = stepsObject.getJSONArray("ingredients");
                if (ingredientArray.length() == 0) {
                    System.out.println("no ingredients provided");
                }else {
                    for (int l = 0; l < ingredientArray.length();l++) {
                        JSONObject ingredientObject = ingredientArray.getJSONObject(l);
                        name = ingredientObject.getString("name");
                        collectionIngredients += name + "\n";
                    }
                    collectionIngredients += name + "\n";
                }

                System.out.println(collectionIngredients);
            }
            title = toTitleCase(resultObject.getString("title").toLowerCase());

                //NUM9 ADDING OBJECTS TO THE ARRAYLIST
                recipeData.add(new RecipeData(title, duration, collectionCuisines,img,collectionIngredients));
            }
            System.out.println("13. returning parsed JSONservice to RecyclerActivity in JSONService");
        return recipeData;
        }


    }


package com.example.recipeproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeproject.Model.JSONService;
import com.example.recipeproject.Model.RecipeData;
import com.example.recipeproject.Model.RecyclerAdapter;
import com.example.recipeproject.Model.myApp;
import com.example.recipeproject.Model.NetworkingService;
import com.example.recipeproject.R;

import org.json.JSONException;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity implements NetworkingService.NetworkingListener, RecyclerAdapter.OnItemClickListener {
    //Interface Num2: implement the clickListener from the Adapter and then generate the method
    RecyclerView recyclerView;
    NetworkingService networkingService;
    JSONService jsonService;
    ArrayList<RecipeData> recipes = new ArrayList<>();//parsed result
    RecyclerAdapter adapter;//don't redeclare it in the onCreate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        networkingService = ((myApp) getApplication()).getNetworkingService();
        System.out.println("0.In the RecyclerActivity");
        jsonService = ((myApp) getApplication()).getJSONService();
        //NUM2 RECEIVE THE STRINGS FROM MAINACTIVITY
        //INTENT HAS TO BE IN ONCREATE
        Intent intent = getIntent();
        String meal = intent.getStringExtra("mealString");
        String diet = intent.getStringExtra("dietString");
        String intolerance = intent.getStringExtra("intolerance");
        System.out.println("1. fetching recipe in RecyclerActivity");
        networkingService.fetchRecipeData(meal, diet, intolerance);
        System.out.println("4. fetching recipe fetched in RecyclerActivity");
        //NUM3 PASS THESE STRINGS AND GO TO THE NETWORKINGSERVICE FETCHRECIPEDATA METHOD
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.println("5a.listener here in RecyclerActivity");
        networkingService.listener = this;
        System.out.println("5. this is the listener applied to RecyclerActivity");
        adapter = new RecyclerAdapter(this,recipes);//OBJECT POPULATING RECYCLERVIEW
        recyclerView.setAdapter(adapter);
        System.out.println("24. recyclerview bound is displaying when the recyclerActivity opens in RecyclerActivity");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.searchMenu);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here");
        //searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    //NUM6 JSONSTRING
    @Override
    public void APINetworkListener(String jsonString) throws JSONException {
        //NUM7 PARSE THE JSONSTRING
        System.out.println("10. in APINetworkListener method in RecyclerActivity");
        System.out.println("11. starting jsonService in RecyclerActivity");
        recipes = jsonService.parseRecipeAPIData(jsonString);
        System.out.println("13. parsed Recipe recieved and saved in variable recipes in RecyclerActivity");//NUM8: parsed JSON

        adapter.ObjList = recipes;
        System.out.println("14.an empty string in RecyclerActivity has the parsed Recipe which is an arraylist. In RecyclerActivity");
        adapter.notifyDataSetChanged();
        System.out.println("15.adapter tells the Recyclerview that the data on the recyclerview has changed. In RecylerActivity");
    }

    @Override
    public void onItemClick(int position) {
        System.out.println("25. When a row in the recyclerview is clicked in RecyclerActivity");
        System.out.println("25a.row clicked "+position);
        System.out.println("26 an intent created to send some data to the RecipeActivity in RecyclerActivity");
        Intent intent = new Intent(this,RecipeActivity2.class);
        intent.putExtra("title",recipes.get(position).getTitle());
        intent.putExtra("ingredients",recipes.get(position).getIngredients());
        System.out.println("26a. title intent printed in RecyclerActivity " + recipes.get(position).getTitle());
        intent.putExtra("image", recipes.get(position).getImg());
        startActivity(intent);
//        System.out.println("27. recipeActivity opens");
    }
}

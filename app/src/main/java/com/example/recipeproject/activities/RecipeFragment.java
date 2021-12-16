package com.example.recipeproject.activities;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recipeproject.R;

public class RecipeFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recipe2, container, false);
        TextView ingredientsText = (TextView)view.findViewById(R.id.ingredients);
        String ingredientsRecieved = getActivity().getIntent().getExtras().getString("ingredients");
        ingredientsText.setText(ingredientsRecieved);
        return view;
    }

    // TODO: Rename and change types and number of parameters
    public static RecipeFragment newInstance(String ingredients) {
        RecipeFragment fragment = new RecipeFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
}
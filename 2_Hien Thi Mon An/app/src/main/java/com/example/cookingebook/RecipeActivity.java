package com.example.cookingebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.textclassifier.TextClassification;
import android.widget.TextView;

public class RecipeActivity extends Activity {
    TextView Name;
    TextView Desc;
    TextView Ingredients;
    TextView CookTime;
    TextView Steps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity);
        Name= findViewById(R.id.textViewName);
        Desc= findViewById(R.id.textViewDesc);
        CookTime= findViewById(R.id.textViewCookTime);
        Ingredients= findViewById(R.id.textViewIngredients);
        Steps=findViewById(R.id.textViewStep);
        LoadData();
    }

    private void LoadData() {
        Intent intent = getIntent();
        Recipe recipe =(Recipe)intent.getSerializableExtra("recipe");
        Name.setText(recipe.getName());
        Desc.setText(recipe.getDesc());
        CookTime.setText("Cook Time: "+recipe.getTotalTime());
        Ingredients.setText(recipe.getIngredients());
        Steps.setText(recipe.getSteps());
    }
}

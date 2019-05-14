package com.example.cookingebook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends Activity {

    private List<Recipe> recipes;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview_activity);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter(this);
    }

    private void initializeData(){
        recipes = new ArrayList<Recipe>();
        Recipe recipe = new Recipe(
                "Chicken with Mayo1 - An Easy Home Recipe",
                "Simple Chicken Mayo with Parmesan and Bread Crumbs 1",
                new String[]{"2 skinless, boneless chicken breasts","2 1/2 tablespoons grated Parmesan cheese","2 tablespoons mayonnaise, or more to taste","2 tablespoons bread crumbs, or more to taste","1 1/2 teaspoons herbes de Provence"},
                new String[]{"Preheat oven to 425 degrees F (220 degrees C). Place chicken breasts in a baking pan.","Mix Parmesan cheese and mayonnaise in a bowl; spread evenly over chicken breasts. Coat with bread crumbs; sprinkle with herbes de Provence.","Bake in the preheated oven until chicken breasts are no longer pink in the center and the juices run clear, 20 to 25 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C)."},
                2,10,0,30,R.drawable.thumbnail1);
        recipes.add(recipe);
        recipe = new Recipe(
                "Spicy BBQ Chicken",
                "The traditional BBQ sauce for grilled chicken (or ribs!) that everyone has had at some point -- very tasty with a little extra personality. You will not regret cooking!",
                new String[]{"2 tablespoons vegetable oil","1/4 cup onion, finely chopped","1 clove garlic, minced","3/4 cup ketchup","1/3 cup vinegar","1 tablespoon Worcestershire sauce","2 teaspoons brown sugar","1 teaspoon dry mustard","1/2 teaspoon salt","1/4 teaspoon black pepper","1/4 (5 ounce) bottle hot pepper sauce","1 (3 pound) chicken, cut into pieces"},
                new String[]{"Heat the oil in a skillet over medium heat and cook the onion and garlic until tender. Mix in ketchup, vinegar, Worcestershire sauce, brown sugar, dry mustard, salt, pepper and hot sauce. Bring to a boil. Reduce heat to low and simmer 10 minutes, stirring occasionally. Remove from heat and set aside.","Preheat grill for high heat.","Lightly oil grill grate. Place chicken on grill. Brush constantly with the sauce and cook 8 to 15 minutes on each side, depending on size of piece, until juices run clear. Discard any remaining sauce."},
                6,20,0,30,R.drawable.thumbnail2);
        recipes.add(recipe);
        recipe = new Recipe(
                "Baked Dijon Salmon",
                "This is a wonderful way to prepare fresh salmon fillets in the oven. Be sure to make extra, your family will be begging for more!",
                new String[]{"2 skinless, boneless chicken breasts","2 1/2 tablespoons grated Parmesan cheese","2 tablespoons mayonnaise, or more to taste","2 tablespoons bread crumbs, or more to taste","1 1/2 teaspoons herbes de Provence"},
                new String[]{"Preheat oven to 425 degrees F (220 degrees C). Place chicken breasts in a baking pan.","Mix Parmesan cheese and mayonnaise in a bowl; spread evenly over chicken breasts. Coat with bread crumbs; sprinkle with herbes de Provence.","Bake in the preheated oven until chicken breasts are no longer pink in the center and the juices run clear, 20 to 25 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C)."},
                2,10,0,30,R.drawable.thumbnail3);
        recipes.add(recipe);
        recipe = new Recipe(
                "One Pot Thai-Style Rice Noodles",
                "Simple Chicken Mayo with Parmesan and Bread Crumbs 4",
                new String[]{"2 skinless, boneless chicken breasts","2 1/2 tablespoons grated Parmesan cheese","2 tablespoons mayonnaise, or more to taste","2 tablespoons bread crumbs, or more to taste","1 1/2 teaspoons herbes de Provence"},
                new String[]{"Preheat oven to 425 degrees F (220 degrees C). Place chicken breasts in a baking pan.","Mix Parmesan cheese and mayonnaise in a bowl; spread evenly over chicken breasts. Coat with bread crumbs; sprinkle with herbes de Provence.","Bake in the preheated oven until chicken breasts are no longer pink in the center and the juices run clear, 20 to 25 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C)."},
                2,10,0,30,R.drawable.thumbnail4);
        recipes.add(recipe);
        recipe = new Recipe(
                "One Pot Easy Cheesy Vegetables and Rice",
                "This easy and cheesy one-pot dish gets to the table in under 15 minutes and is a great way to add more vegetables to your meal. Bonus – only one pot to clean!",
                new String[]{"2 skinless, boneless chicken breasts","2 1/2 tablespoons grated Parmesan cheese","2 tablespoons mayonnaise, or more to taste","2 tablespoons bread crumbs, or more to taste","1 1/2 teaspoons herbes de Provence"},
                new String[]{"Preheat oven to 425 degrees F (220 degrees C). Place chicken breasts in a baking pan.","Mix Parmesan cheese and mayonnaise in a bowl; spread evenly over chicken breasts. Coat with bread crumbs; sprinkle with herbes de Provence.","Bake in the preheated oven until chicken breasts are no longer pink in the center and the juices run clear, 20 to 25 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C)."},
                2,10,0,30,R.drawable.thumbnail5);
        recipes.add(recipe);
        recipe = new Recipe(
                "NO YOLKS® One Pot Cheesy Taco Noodles",
                "A delicious twist on taco night, this hearty noodle toss can be garnished to taste with your favourite Tex Mex fixings, such as crumbled tortilla chips, sour cream, shredded lettuce, green onion and diced tomatoes.",
                new String[]{"2 skinless, boneless chicken breasts","2 1/2 tablespoons grated Parmesan cheese","2 tablespoons mayonnaise, or more to taste","2 tablespoons bread crumbs, or more to taste","1 1/2 teaspoons herbes de Provence"},
                new String[]{"Preheat oven to 425 degrees F (220 degrees C). Place chicken breasts in a baking pan.","Mix Parmesan cheese and mayonnaise in a bowl; spread evenly over chicken breasts. Coat with bread crumbs; sprinkle with herbes de Provence.","Bake in the preheated oven until chicken breasts are no longer pink in the center and the juices run clear, 20 to 25 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C)."},
                2,10,0,30,R.drawable.thumbnail6);
        recipes.add(recipe);
        recipe = new Recipe(
                "NO YOLKS® Maple Balsamic Glazed Salmon with Noodles",
                "The salmon and olive oil in this recipe contain heart healthy omega 3 and monounsaturated fatty acids. This dish comes together with a sweet and tangy balsamic vinaigrette, but you can shortcut the prep by using any favourite bottled balsamic dressing.",
                new String[]{"2 skinless, boneless chicken breasts","2 1/2 tablespoons grated Parmesan cheese","2 tablespoons mayonnaise, or more to taste","2 tablespoons bread crumbs, or more to taste","1 1/2 teaspoons herbes de Provence"},
                new String[]{"Preheat oven to 425 degrees F (220 degrees C). Place chicken breasts in a baking pan.","Mix Parmesan cheese and mayonnaise in a bowl; spread evenly over chicken breasts. Coat with bread crumbs; sprinkle with herbes de Provence.","Bake in the preheated oven until chicken breasts are no longer pink in the center and the juices run clear, 20 to 25 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C)."},
                2,10,0,30,R.drawable.thumbnail7);
        recipes.add(recipe);
        recipe = new Recipe(
                "NO YOLKS® Cider Glazed Pork with Noodles",
                "This weekend dinner features the classic pairing of pork and apples in a dish that is stylish enough to serve to company but still very easy to prepare.",
                new String[]{"2 skinless, boneless chicken breasts","2 1/2 tablespoons grated Parmesan cheese","2 tablespoons mayonnaise, or more to taste","2 tablespoons bread crumbs, or more to taste","1 1/2 teaspoons herbes de Provence"},
                new String[]{"Preheat oven to 425 degrees F (220 degrees C). Place chicken breasts in a baking pan.","Mix Parmesan cheese and mayonnaise in a bowl; spread evenly over chicken breasts. Coat with bread crumbs; sprinkle with herbes de Provence.","Bake in the preheated oven until chicken breasts are no longer pink in the center and the juices run clear, 20 to 25 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C)."},
                2,10,0,30,R.drawable.thumbnail8);
        recipes.add(recipe);
        recipe = new Recipe(
                "NO YOLKS® Skillet Salsa Chicken with Noodles",
                "The added protein from black beans adds a fibre boost and also allows you to use less ground chicken in this budget conscious variation on the traditional taco supper.",
                new String[]{"2 skinless, boneless chicken breasts","2 1/2 tablespoons grated Parmesan cheese","2 tablespoons mayonnaise, or more to taste","2 tablespoons bread crumbs, or more to taste","1 1/2 teaspoons herbes de Provence"},
                new String[]{"Preheat oven to 425 degrees F (220 degrees C). Place chicken breasts in a baking pan.","Mix Parmesan cheese and mayonnaise in a bowl; spread evenly over chicken breasts. Coat with bread crumbs; sprinkle with herbes de Provence.","Bake in the preheated oven until chicken breasts are no longer pink in the center and the juices run clear, 20 to 25 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C)."},
                2,10,0,30,R.drawable.thumbnail9);
        recipes.add(recipe);
        recipe = new Recipe(
                "NO YOLKS® Creamy Chicken Divan",
                "Maximize your time in the kitchen by preparing the whole bag of noodles. Use the extra noodles to prepare our NO YOLKS Garlicky Noodles with Kale* while the casserole is baking and you'll have tomorrow's lunch ready too!",
                new String[]{"2 skinless, boneless chicken breasts","2 1/2 tablespoons grated Parmesan cheese","2 tablespoons mayonnaise, or more to taste","2 tablespoons bread crumbs, or more to taste","1 1/2 teaspoons herbes de Provence"},
                new String[]{"Preheat oven to 425 degrees F (220 degrees C). Place chicken breasts in a baking pan.","Mix Parmesan cheese and mayonnaise in a bowl; spread evenly over chicken breasts. Coat with bread crumbs; sprinkle with herbes de Provence.","Bake in the preheated oven until chicken breasts are no longer pink in the center and the juices run clear, 20 to 25 minutes. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C)."},
                2,10,0,30,R.drawable.thumbnail10);
        recipes.add(recipe);
    }

    private void initializeAdapter(Context context){
        RVAdapter adapter = new RVAdapter(recipes,context);
        rv.setAdapter(adapter);
    }
}

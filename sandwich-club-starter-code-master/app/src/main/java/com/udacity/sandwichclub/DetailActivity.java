package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;


import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    ImageView ingredientsIv;
    TextView mainName,alsoKnownAs,placeOfOrigin,description,Ingredients;
    Sandwich sandwich ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);
        placeOfOrigin = findViewById(R.id.origin_tv);
        alsoKnownAs = findViewById(R.id.also_known_tv);
        description = findViewById(R.id.description_tv);
        Ingredients = findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];


         sandwich = JsonUtils.parseSandwichJson(json);


        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());




    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        if(!sandwich.getPlaceOfOrigin().isEmpty()){ placeOfOrigin.append(sandwich.getPlaceOfOrigin() + "\n");}
       else  placeOfOrigin.setText("no data for  placeOfOrigin");

        if(!sandwich.getDescription().isEmpty()){description.append(sandwich.getDescription() + "\n");}
        else
        description.setText("no data for description");

        List<String>alsokonwn=sandwich.getAlsoKnownAs();
        List<String> Ingredient=sandwich.getIngredients();

        for (int i = 0; i < alsokonwn.size(); i++) {
        alsoKnownAs.append(alsokonwn.get(i)+"\n");
    }

        for (int i = 0; i < Ingredient.size(); i++) {
            Ingredients.append(Ingredient.get(i) + "\n");
        }






    }
}

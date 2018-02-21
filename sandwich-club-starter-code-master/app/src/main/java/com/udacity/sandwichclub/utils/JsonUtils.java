package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils  {

    public static  List<String> alsoKnownAs = new ArrayList<>();
    public static  List<String> ingredients = new ArrayList<>();

    public static Sandwich parseSandwichJson(String json) {

        String image = null;
        String description = null;
        String mainName = null, placeOfOrigin = null;



        try {
            JSONObject jb = new JSONObject(json);
            JSONObject name = jb.getJSONObject("name");
            mainName = name.getString("mainName");
            placeOfOrigin = jb.getString("placeOfOrigin");
            description = jb.getString("description");
            image = jb.getString("image");
            JSONArray jsonArrayalsoKnownAs = name.getJSONArray("alsoKnownAs");
            alsoKnownAs.clear();
            ingredients.clear();
             if (jsonArrayalsoKnownAs!= null) {
                for (int i = 0; i < jsonArrayalsoKnownAs.length(); i++) {
                    alsoKnownAs.add(jsonArrayalsoKnownAs.getString(i));
                }

            }
            else {
                alsoKnownAs.add("no data");
            }

             JSONArray jsonArraying = jb.getJSONArray("ingredients");

             if (jsonArraying!= null) {
                for (int j = 0; j < jsonArraying.length(); j++) {
                    ingredients.add(jsonArraying.getString(j));

                }
            }
            else {
                ingredients.add("no data");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        Sandwich s = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        s.setMainName(mainName);
        s.setAlsoKnownAs(alsoKnownAs);
        s.setPlaceOfOrigin(placeOfOrigin);
        s.setDescription(description);
        s.setImage(image);
        s.setIngredients(ingredients);


        return s;



    }


}

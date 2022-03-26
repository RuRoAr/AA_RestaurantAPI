package com.svalero.restaurantapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class RestaurantDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);

        Intent intent = getIntent();
        String name = intent.getStringExtra("recomendationRestaurant");

        TextView etRestaurantDetail = findViewById(R.id.restaurant_detail_name);
        etRestaurantDetail.setText(name);

}
}
package com.svalero.restaurantapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.svalero.restaurantapi.domain.Restaurant;

public class RestaurantDetail extends AppCompatActivity {
    Restaurant restaurant = new Restaurant("","","",0,"",0,"");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_restaurant);

        Intent intent8 = getIntent();
        String recomendation = intent8.getStringExtra("recomendation");
        TextView etRestaurantDetail = findViewById(R.id.restaurant_detail_name);
        etRestaurantDetail.setText(recomendation);
}

    public void back(View view){
        Intent intent= new Intent(this, MainActivity.class);//carga la clase
        startActivity(intent);
    }
}
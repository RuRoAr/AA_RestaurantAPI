package com.svalero.restaurantapi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.svalero.restaurantapi.batabase.AppDatabase;
import com.svalero.restaurantapi.domain.Restaurant;

public class ModifyRestaurant extends AppCompatActivity {
    Restaurant restaurant = new Restaurant("","","",0,"",0,"");
    private  int modify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_restaurant);
        Intent intent = getIntent();
        modify = intent.getIntExtra("modify", 0);
        //TextView tvInfo = findViewById(R.id.modify_visit_info);
        String restaurantName = intent.getStringExtra("restaurantName");

        restaurant = (Restaurant) intent.getSerializableExtra("Restaurant");

        EditText etName = findViewById(R.id.restaurant_name);
        EditText etAddress = findViewById(R.id.restaurant_address);
        EditText etTypeFood = findViewById(R.id.restaurant_type_food);
        EditText etQualification = findViewById(R.id.restaurant_qualification);
        EditText etRecomendation = findViewById(R.id.restaurrant_recommendation);
        EditText etMediumPrice = findViewById(R.id.restaurant_medium_price);
        EditText etGoBack = findViewById(R.id.restaurant_go_back);

        etName.setText(restaurant.getName());
        etAddress.setText(restaurant.getAddress());
        etGoBack.setText(restaurant.getGoBack());
        etMediumPrice.setText(Float.toString( restaurant.getMediumPrice()));
        etTypeFood.setText(restaurant.getTypeFood());
        etRecomendation.setText(restaurant.getRecommendation());
        etQualification.setText(Float.toString(restaurant.getQualification()));



    }
    public  void modify (View view){
        Toast.makeText(this, R.string.escrinir_todo, Toast.LENGTH_SHORT).show();//aperece un mesaje tostada

        EditText etName = findViewById(R.id.restaurant_name);
        EditText etAddress = findViewById(R.id.restaurant_address);
        EditText etTypeFood = findViewById(R.id.restaurant_type_food);
        EditText etQualification = findViewById(R.id.restaurant_qualification);
        EditText etRecomendation = findViewById(R.id.restaurrant_recommendation);
        EditText etMediumPrice = findViewById(R.id.restaurant_medium_price);
        EditText etGoBack = findViewById(R.id.restaurant_go_back);

        String name = etName.getText().toString();
        String address = etAddress.getText().toString();
        String typeFood = etTypeFood.getText().toString();
        String qualificationString =  etQualification.getText().toString();
        String recomendation = etRecomendation.getText().toString();
        String mediumPriceString = etMediumPrice.getText().toString();
        String goBack = etGoBack.getText().toString();

        if((name.equals(""))||(address.equals(""))||(typeFood.equals(""))||(qualificationString.equals(""))
                ||(recomendation.equals(""))||(mediumPriceString.equals(""))||(goBack.equals(""))){
            Toast.makeText(this, R.string.escrinir_todo, Toast.LENGTH_SHORT).show();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog.Builder builder1 = builder.setMessage(R.string.confirmar)
                    .setPositiveButton(R.string.si,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    float qualification = Float.parseFloat(qualificationString);
                                    float mediumPrice = Float.parseFloat(mediumPriceString);
                                    restaurant.setName(name);
                                    restaurant.setAddress(address);
                                    restaurant.setQualification(qualification);
                                    restaurant.setTypeFood(typeFood);
                                    restaurant.setRecommendation(recomendation);
                                    restaurant.setGoBack(goBack);
                                    restaurant.setMediumPrice(mediumPrice);
                                    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                                            AppDatabase.class, "restaurants").allowMainThreadQueries().build();

                                    db.restaurantDao().update(restaurant);

                                    etName.setText("");
                                    etAddress.setText("");
                                    etQualification.setText("");
                                    etTypeFood.setText("");
                                    etQualification.setText("");
                                    etRecomendation.setText("");
                                    etGoBack.setText("");
                                    etMediumPrice.setText("");
                                }
                            })
                    .setNegativeButton(R.string.no,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
            builder.create().show();


        }}
}
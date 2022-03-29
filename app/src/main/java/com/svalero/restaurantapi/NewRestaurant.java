package com.svalero.restaurantapi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.svalero.restaurantapi.batabase.AppDatabase;
import com.svalero.restaurantapi.domain.Restaurant;

import java.time.LocalDate;

public class NewRestaurant extends AppCompatActivity {

    Restaurant restaurant = new Restaurant("","","",0,"",0,"");
    private  int modify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_restaurant);

        Intent intent = getIntent();
        modify = intent.getIntExtra("modify", 0);
        //TextView tvInfo = findViewById(R.id.modify_visit_info);
        String restaurantName = intent.getStringExtra("restaurantName");

        restaurant = (Restaurant) intent.getSerializableExtra("Restaurant");





    }
    public void add(View view){ //con el parametri View wl otro es el que quierasView,
        // que lo hace cunado le damos al boton añdair


        EditText etName = findViewById(R.id.restaurant_name);
        EditText etAddress = findViewById(R.id.restaurant_address);
        EditText etTypeFood = findViewById(R.id.restaurant_type_food);
        EditText etQualification = findViewById(R.id.restaurant_qualification);
        EditText etRecomendation = findViewById(R.id.restaurrant_recommendation);
        EditText etMediumPrice = findViewById(R.id.restaurant_medium_price);
        EditText etGoBack = findViewById(R.id.restaurant_go_back);



        if (( etName.getText().toString().equals(""))
                || (etGoBack.getText().toString().equals(""))
                || (etAddress.getText().toString().equals(""))
                || (etQualification.getText().toString().equals(""))
                || (etMediumPrice.getText().toString().equals(""))
                || (etTypeFood.getText().toString().equals(""))
                || (etRecomendation.getText().toString().equals("")))
        {
            Toast.makeText(this, "Debes escribir todos los campos", Toast.LENGTH_SHORT).show();//aperece un mesaje tostada
        }else {
            Restaurant restaurant = new Restaurant(//Creamos el nuevo objeto para meter los valores
                    etName.getText().toString(),
                    etAddress.getText().toString(),
                    etTypeFood.getText().toString(),
                    Float.parseFloat(etQualification.getText().toString()),
                    etRecomendation.getText().toString(),
                    Float.parseFloat(etMediumPrice.getText().toString()),
                    etGoBack.getText().toString());
            // MainActivity.restaurants.add(restaurant);
            //con esta linea se instancia la bbdd
            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "restaurants").allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
            //ahora ya puedo llamar a la base de datos
            db.restaurantDao().insert(restaurant);
            Toast.makeText(this, "Restaurante " + restaurant + " Añadido", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etAddress.setText("");
            etGoBack.setText("");
            etMediumPrice.setText("");
            etRecomendation.setText("");
            etTypeFood.setText("");
            etQualification.setText("");
            etName.requestFocus();
        }
    }

}
package com.svalero.restaurantapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.svalero.restaurantapi.batabase.AppDatabase;
import com.svalero.restaurantapi.domain.Restaurant;
import com.svalero.restaurantapi.domain.Wine;

public class NewWine extends AppCompatActivity {
    private Wine wine = new Wine("","",0,"",0,0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_wine);
      //  Intent intent = getIntent();

      //  modify = intent.getIntExtra("modify", 0);
        //TextView tvInfo = findViewById(R.id.modify_visit_info);
      //  String wineName = intent.getStringExtra("wineName");

      //  wine = (Wine) intent.getSerializableExtra("Wine");
    }
    public void add(View view){ //con el parametri View wl otro es el que quierasView,
        // que lo hace cunado le damos al boton añdair



        EditText etName = findViewById(R.id.wine_name);
        EditText etType = findViewById(R.id.wine_type);
        EditText etAge = findViewById(R.id.wine_price);
        EditText etWineCellar = findViewById(R.id.wine_cellar);
        EditText etPrice = findViewById(R.id.wine_price);
        EditText etQualification = findViewById(R.id.wine_qualification);




        if (( etName.getText().toString().equals(""))
                || (etType.getText().toString().equals(""))
                || (etAge.getText().toString().equals(""))
                || (etQualification.getText().toString().equals(""))
                || (etPrice.getText().toString().equals(""))
                || (etWineCellar.getText().toString().equals("")))
        {
            Toast.makeText(this, R.string.escrinir_todo, Toast.LENGTH_SHORT).show();//aperece un mesaje tostada
        }else {
            Wine wine = new Wine(//Creamos el nuevo objeto para meter los valores
                    etName.getText().toString(),
                    etType.getText().toString(),
                    Integer.parseInt(etAge.getText().toString()),
                    etWineCellar.getText().toString(),
                    Float.parseFloat(etPrice.getText().toString()),
                    Float.parseFloat(etQualification.getText().toString()));

            // MainActivity.restaurants.add(restaurant);
            //con esta linea se instancia la bbdd
            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "wines").allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
            //ahora ya puedo llamar a la base de datos
            db.wineDao().insert(wine);
            Toast.makeText(this, "Vino " + wine + " Añadido", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etType.setText("");
            etAge.setText("");
            etQualification.setText("");
            etWineCellar.setText("");
            etPrice.setText("");


            etName.requestFocus();
        }
    }
}
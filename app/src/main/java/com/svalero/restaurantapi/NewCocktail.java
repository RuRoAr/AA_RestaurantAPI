package com.svalero.restaurantapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.svalero.restaurantapi.batabase.AppDatabase;
import com.svalero.restaurantapi.domain.Cocktail;
import com.svalero.restaurantapi.domain.Wine;

public class NewCocktail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cocktail);
    }
    public void add(View view){ //con el parametri View wl otro es el que quierasView,
        // que lo hace cunado le damos al boton añdair




        EditText etName = findViewById(R.id.cocktail_name);
        EditText etGrade = findViewById(R.id.cocktail_grade);
        EditText etIngredient = findViewById(R.id.cocktail_ingredients);
        EditText etPrice = findViewById(R.id.cocktail_price);
        EditText etQualification = findViewById(R.id.cocktail_qualification);
        CheckBox checkBox = findViewById(R.id.checkBox);




        if (( etName.getText().toString().equals(""))
                || (etGrade.getText().toString().equals(""))
                || (etIngredient.getText().toString().equals(""))
                || (etQualification.getText().toString().equals(""))
                || (etPrice.getText().toString().equals("")))
        {
            Toast.makeText(this, "Debes escribir todos los campos", Toast.LENGTH_SHORT).show();//aperece un mesaje tostada
        }else {

            Cocktail cocktail = new Cocktail(//Creamos el nuevo objeto para meter los valores
                    etName.getText().toString(),
                    Integer.parseInt(etGrade.getText().toString()),
                    etIngredient.getText().toString(),
                    Float.parseFloat(etPrice.getText().toString()),
                    Float.parseFloat(etQualification.getText().toString()),
                    checkBox.isChecked());
            boolean checkbox = checkBox.isChecked();

            // MainActivity.restaurants.add(restaurant);
            //con esta linea se instancia la bbdd
            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "cocktails").allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
            //ahora ya puedo llamar a la base de datos
            db.cocktailDao().insert(cocktail);
            Toast.makeText(this, "Cocktail " + cocktail + " Añadido", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etGrade.setText("");
            etIngredient.setText("");
            etQualification.setText("");
            etPrice.setText("");



            etName.requestFocus();
        }
    }
}
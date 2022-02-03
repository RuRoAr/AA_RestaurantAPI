package com.svalero.restaurantapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.svalero.restaurantapi.domain.Restaurant;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{// el lisener es para escuchar el click de la pantalla {

    public static ArrayList<Restaurant> restaurants;// necesito tener una lista para los Restaurantes, lista de la BBDD
    private ArrayAdapter<Restaurant> restaurantsAdapter;//objeto android que hace que el lv liste todo el arrayList


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurants = new ArrayList<>();// la inicializamos para que en cuanto cargue la cree de primeras

        ListView lvRestaurants = findViewById(R.id.restaurants_list);//asignamos la lista al layout
        restaurantsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        // las dos lista para que se pueda ver, el list_item_1 es untipo de lista ya precocinado
        // que esta ya predertiemado ese tipo de listas(se ouede cambiar)

        lvRestaurants.setAdapter(restaurantsAdapter);//aqui se emparejan
    }


    private void makeSummary(){// suma los restuarante y los pone en una lista
        int costRestaurant = restaurants.size();
        double totalCost =restaurants.stream()//coge la lista de restaurantes lo combierte en stream
                .map(Restaurant::getMediumPrice)// paso a tener una lista de preciosMedios
                .mapToDouble(mediumPrice -> mediumPrice)//aqui los voy sumando
                .sum();

        TextView tvSummary = findViewById(R.id.summary);

            tvSummary.setText("llevas gastado en restaurantes " + totalCost + " €");
    }

    @Override// boton derecho methos, te infla el menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {// boton derecho methos, aqui le pondremos
        // a la pestaña de donde queremos ir
        if (item.getItemId() == R.id.new_restuarant) {
            Intent intent = new Intent(this, NewRestaurant.class);//con este obejeto es
            // con el que le decimos a la clase a la que queremos ir
            startActivity(intent);
            return true;
        }

        return false;
    }
    @Override
    protected void onResume() {//otra forma de actualizar la lista
        super.onResume();

        restaurantsAdapter.notifyDataSetChanged();
        makeSummary();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Restaurant restaurant = restaurants.get(position);//cogo la posicion en la que esta el restaurante que voy a ver el detalle
        //la posicion me la da el metodo
        Intent intent= new Intent(this, RestaurantDetail.class);//carga la clase
        intent.putExtra("name", restaurant.getName());//con esto le pasa valores que luego se pintan en el produc detail
        startActivity(intent);
    }
}
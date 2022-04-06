package com.svalero.restaurantapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.ContextMenu;
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

import com.svalero.restaurantapi.batabase.AppDatabase;
import com.svalero.restaurantapi.domain.Restaurant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{// el lisener es para escuchar el click de la pantalla {

    public List<Restaurant> restaurants;// necesito tener una lista para los Restaurantes, lista de la BBDD
    private ArrayAdapter<Restaurant> restaurantsAdapter;//objeto android que hace que el lv liste todo el arrayList

   // Restaurant restaurant = new Restaurant("","","",0,"",0,"");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurants = new ArrayList<>();// la inicializamos para que en cuanto cargue la cree de primeras

        ListView lvRestaurants = findViewById(R.id.restaurants_list);//asignamos la lista al layout
        restaurantsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, restaurants);
        // las dos lista para que se pueda ver, el list_item_1 es untipo de lista ya precocinado
        // que esta ya predertiemado ese tipo de listas(se ouede cambiar)

        ListView lvLista = (ListView) findViewById(R.id.restaurants_list);
        registerForContextMenu(lvLista);//registra la lista en el menu contxtual

        lvRestaurants.setAdapter(restaurantsAdapter);//aqui se emparejan

        lvRestaurants.setOnItemClickListener(this);
    }


    private void makeSummary(){// suma los restuarante y los pone en una lista
        int costRestaurant = restaurants.size();
        double totalCost =restaurants.stream()//coge la lista de restaurantes lo combierte en stream
                .map(Restaurant::getMediumPrice)// paso a tener una lista de preciosMedios
                .mapToDouble(mediumPrice -> mediumPrice)//aqui los voy sumando
                .sum();

        TextView tvSummary = findViewById(R.id.summary);

        tvSummary.setText(getString(R.string.summary_wines,totalCost));

            tvSummary.setText(getString(R.string.gastado_en_restaurantes) + totalCost + " €");
    }

    @Override// boton derecho methos, te infla el menu
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {// boton derecho methos, aqui le pondremos
        // a la pestaña de donde queremos ir


        switch (item.getItemId()) {
            case R.id.new_restuarant:
                Intent intent1 = new Intent(this, NewRestaurant.class);
                startActivity(intent1);
                return true;
            case R.id.list_wine:
                Intent intent4 = new Intent(this, ListWine.class);
                startActivity(intent4);
                return true;
            case R.id.list_cocktail:
                Intent intent3 = new Intent(this, ListCocktail.class);
                startActivity(intent3);
                return true;
            case R.id.restaurant_found:
                Intent intent2 = new Intent(this, RestaurantFound.class);
                startActivity(intent2);
                return true;
            case R.id.camera:
                Intent intent5 = new Intent(this, PhotoCamera.class);
                startActivity(intent5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


//        if (item.getItemId() == R.id.new_restuarant) {
//            Intent intent = new Intent(this, NewRestaurant.class);//con este obejeto es
//            // con el que le decimos a la clase a la que queremos ir
//            startActivity(intent);
//            return true;
//        }
//
//        return false;
    }
    @Override
    protected void onResume() {//otra forma de actualizar la lista
        super.onResume();

        loadRestaurant();// lo que hace es que llama al metodo de volver a cargar la lista de la bbdd
        restaurantsAdapter.notifyDataSetChanged();
        makeSummary();
    }

    private void loadRestaurant(){
        restaurants.clear();

        AppDatabase db = Room.databaseBuilder(getApplicationContext()
                ,AppDatabase.class, "restaurants").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        restaurants.addAll(db.restaurantDao().getAll());//el addAll es para que apunte a la misma lista
    }

    //crea el menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context, menu);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Restaurant restaurant = restaurants.get(position);//cojo la posicion en la que esta el restaurante que voy a ver el detalle
        //la posicion me la da el metodo
        Intent intent= new Intent(this, ModifyRestaurant.class);//carga la clase
        intent.putExtra("modify", 1);
        intent.putExtra("nameRestaurant", restaurant.getName());
        intent.putExtra("Restaurant", restaurant);
       // intent.putExtra("name", restaurant.getName());//con esto le pasa valores que luego se pintan en el produc detail
        startActivity(intent);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
//            case R.id.detail:
//                final int itemSeleccionado = info.position;
//                Restaurant restaurant = restaurants.get(itemSeleccionado);
//                Intent intent8 = new Intent(this, PhotoCamera.class);
//                intent8.putExtra("recomendation", restaurant.getRecommendation());
//                startActivity(intent8);
            case R.id.borrar:
                deleteRestaurant(info);
                return true;
            default:
        return super.onContextItemSelected(item);
    }}

    public void  deleteRestaurant(AdapterView.AdapterContextMenuInfo info){
        Restaurant restaurant = restaurants.get(info.position);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "restaurants").allowMainThreadQueries().build();
        db.restaurantDao().delete(restaurant);
        finish();
        startActivity(getIntent());
    }
}
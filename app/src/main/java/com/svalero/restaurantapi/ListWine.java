package com.svalero.restaurantapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.svalero.restaurantapi.batabase.AppDatabase;
import com.svalero.restaurantapi.domain.Restaurant;
import com.svalero.restaurantapi.domain.Wine;

import java.util.ArrayList;
import java.util.List;

public class ListWine extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public List<Wine> wines;
    private ArrayAdapter<Wine> winesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_wine);
        wines = new ArrayList<>();

        ListView lvWines = findViewById(R.id.wine_list);
        winesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wines);


        ListView lvLista = (ListView) findViewById(R.id.wine_list);
        registerForContextMenu(lvLista);//registra la lista en el menu contxtual

        lvWines.setAdapter(winesAdapter);//aqui se emparejan

        lvWines.setOnItemClickListener( this);
    }

    private void makeSummary(){// suma los restuarante y los pone en una lista
        int costWines = wines.size();
        double totalCost =wines.stream()//coge la lista de restaurantes lo combierte en stream
                .map(Wine::getPrice)// paso a tener una lista de preciosMedios
                .mapToDouble(Price -> Price)//aqui los voy sumando
                .sum();

        TextView tvSummary = findViewById(R.id.summary_wine);

        tvSummary.setText(getString(R.string.summary_wines,totalCost));
    }



    @Override
    protected void onResume() {//otra forma de actualizar la lista
        super.onResume();

        loadWine();// lo que hace es que llama al metodo de volver a cargar la lista de la bbdd
        winesAdapter.notifyDataSetChanged();
        makeSummary();
    }

    private void loadWine(){
        wines.clear();

        AppDatabase db = Room.databaseBuilder(getApplicationContext()
                ,AppDatabase.class, "wines").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        wines.addAll(db.wineDao().getAll());//el addAll es para que apunte a la misma lista
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Wine wine = wines.get(position);//cojo la posicion en la que esta el restaurante que voy a ver el detalle
        //la posicion me la da el metodo
        Intent intent= new Intent(this, NewWine.class);//carga la clase
        intent.putExtra("modify", 1);
        intent.putExtra("nameWine", wine.getName());
        intent.putExtra("Wine", wine);
        // intent.putExtra("name", restaurant.getName());//con esto le pasa valores que luego se pintan en el produc detail
        startActivity(intent);
    }
    public boolean addWine(View view){
        Intent intent = new Intent(this, NewWine.class);//con este obejeto es

            startActivity(intent);
            return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.deletes, menu);
    }

        @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.borrar_wine:
                deleteWine(info);
                return true;
            default:
                return super.onContextItemSelected(item);
        }}

    public void  deleteWine(AdapterView.AdapterContextMenuInfo info){
        Wine wine = wines.get(info.position);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "wines").allowMainThreadQueries().build();
        db.wineDao().delete(wine);
        finish();
        startActivity(getIntent());
    }
}
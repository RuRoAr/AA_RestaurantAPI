package com.svalero.restaurantapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
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
import com.svalero.restaurantapi.domain.Cocktail;
import com.svalero.restaurantapi.domain.Restaurant;
import com.svalero.restaurantapi.domain.Wine;

import java.util.ArrayList;
import java.util.List;

public class ListCocktail extends AppCompatActivity implements AdapterView.OnItemClickListener {
    public List<Cocktail> cocktails;
    private ArrayAdapter<Cocktail> cocktailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cocktail);

        cocktails = new ArrayList<>();

        ListView lvCocktails = findViewById(R.id.cocktail_list);
        cocktailsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cocktails);


        ListView lvLista = (ListView) findViewById(R.id.cocktail_list);
        registerForContextMenu(lvLista);//registra la lista en el menu contxtual

        lvCocktails.setAdapter(cocktailsAdapter);//aqui se emparejan

        lvCocktails.setOnItemClickListener(this);
    }

    private void makeSummary() {// suma los restuarante y los pone en una lista
        int costCocktails = cocktails.size();
        double totalCost = cocktails.stream()//coge la lista de restaurantes lo combierte en stream
                .map(Cocktail::getPrice)// paso a tener una lista de preciosMedios
                .mapToDouble(Price -> Price)//aqui los voy sumando
                .sum();

        TextView tvSummary = findViewById(R.id.summary_cocktails);

        tvSummary.setText(getString(R.string.gastado_en_cocktails) + totalCost + " €");
    }

    @Override
    protected void onResume() {//otra forma de actualizar la lista
        super.onResume();

        loadCocktail();// lo que hace es que llama al metodo de volver a cargar la lista de la bbdd
        cocktailsAdapter.notifyDataSetChanged();
        makeSummary();
    }

    private void loadCocktail() {
        cocktails.clear();

        AppDatabase db = Room.databaseBuilder(getApplicationContext()
                , AppDatabase.class, "cocktails").allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        cocktails.addAll(db.cocktailDao().getAll());//el addAll es para que apunte a la misma lista
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cocktail cocktail = cocktails.get(position);//cojo la posicion en la que esta el restaurante que voy a ver el detalle
        //la posicion me la da el metodo
        Intent intent = new Intent(this, NewCocktail.class);//carga la clase
        intent.putExtra("modify", 1);
        intent.putExtra("nameCocktail", cocktail.getName());
        intent.putExtra("Cocktail", cocktail);
        // intent.putExtra("name", restaurant.getName());//con esto le pasa valores que luego se pintan en el produc detail
        startActivity(intent);
    }

    public boolean addCocktail(View view) {
        Intent intent = new Intent(this, NewCocktail.class);//con este obejeto es
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
                deleteCocktail(info);
                return true;
            default:
                return super.onContextItemSelected(item);
        }}

    public void  deleteCocktail(AdapterView.AdapterContextMenuInfo info){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog.Builder builder1 = builder.setMessage(R.string.confirmar)
                .setPositiveButton(R.string.si,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Cocktail cocktail= cocktails.get(info.position);
                                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                                        AppDatabase.class, "cocktails").allowMainThreadQueries().build();
                                db.cocktailDao().delete(cocktail);
                                finish();
                                startActivity(getIntent());
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
    }
}


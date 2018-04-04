package com.example.adama.sampleasso2bis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rcvMain;
    private ArrayList<String> assoPokemon;
    private Call<PokemonResults> pokemonCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvMain = findViewById(R.id.rcvMain);

        assoPokemon = new ArrayList<>();

        IPoke poke = new Retrofit.Builder()
                .baseUrl(IPoke.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IPoke.class);

        pokemonCall = poke.getAllPokemons();

    }

    @Override
    protected void onStart() {
        super.onStart();

        //Agencement de la recyclerview
        rcvMain.setLayoutManager(new LinearLayoutManager(this));

        //Instancier l'adapter
        final Adapter adapter = new Adapter(this);

        //Ajout de l'adapter
        rcvMain.setAdapter(adapter);

        pokemonCall.enqueue(new Callback<PokemonResults>() {
            @Override
            public void onResponse(Call<PokemonResults> call, Response<PokemonResults> response) {
                adapter.resetData(response.body().getResults());
            }

            @Override
            public void onFailure(Call<PokemonResults> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erreur : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", "onFailure: "+t.getMessage());
            }
        });
    }
}

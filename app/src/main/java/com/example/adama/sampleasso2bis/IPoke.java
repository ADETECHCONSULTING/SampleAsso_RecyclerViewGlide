package com.example.adama.sampleasso2bis;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Adama on 01/02/2018.
 */

public interface IPoke {

    public static String ENDPOINT = "http://pokeapi.co/api/v2/";


    @GET("pokemon/{number}")
    Call<DetailPokemon> getPokemonById(@Path("number") int number);

    @GET("pokemon/")
    Call<PokemonResults> getAllPokemons();
}

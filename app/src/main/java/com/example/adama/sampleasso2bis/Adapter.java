package com.example.adama.sampleasso2bis;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Adama on 11/01/2018.
 */

public class Adapter extends RecyclerView.Adapter {

    private List<Pokemon> _items = new ArrayList<>();
    private Activity activity;
    private IPoke _Poke;

    public Adapter(Activity activity, IPoke poke){
        this.activity = activity;
        this._Poke = poke;
    }

    public void resetData(List<Pokemon> items){
        _items = items;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        //manipulation de view ici
        final VHolder vHolder = new VHolder(view);
        vHolder.ctnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<DetailPokemon> pokemonCall = _Poke.getPokemonById(vHolder.getAdapterPosition()+1);

                pokemonCall.enqueue(new Callback<DetailPokemon>() {
                    @Override
                    public void onResponse(Call<DetailPokemon> call, Response<DetailPokemon> response) {
                        Glide.with(activity).load(response.body().getSprites().getFront_default()).into(vHolder.imgPokemon);
                    }

                    @Override
                    public void onFailure(Call<DetailPokemon> call, Throwable t) {
                        Toast.makeText(activity, "Erreur : "+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VHolder vHolder = (VHolder) holder; //Cast du holder reçu en parametre

        vHolder.lblRecycler.setText(_items.get(position).getName());
    }

    @Override
    public int getItemCount() {

        return _items.size();
    }

    class VHolder extends RecyclerView.ViewHolder {
        public TextView lblRecycler;
        public ImageView imgPokemon;
        public ViewGroup ctnMain;

        public VHolder(View itemView) {
            super(itemView);

            lblRecycler = itemView.findViewById(R.id.lblRecycler);
            imgPokemon = itemView.findViewById(R.id.imgPokemon);
            ctnMain = itemView.findViewById(R.id.ctnMain);
        }
    }
}

package com.example.adama.sampleasso2bis;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adama on 11/01/2018.
 */

public class Adapter extends RecyclerView.Adapter {

    private List<Pokemon> _items = new ArrayList<>();
    private Activity activity;

    public Adapter(Activity activity){
        this.activity = activity;
    }

    public void resetData(List<Pokemon> items){
        _items = items;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items, parent, false);
        //manipulation de view ici
        return new VHolder(view);
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

        public VHolder(View itemView) {
            super(itemView);

            lblRecycler = itemView.findViewById(R.id.lblRecycler);

        }
    }
}

package com.example.labweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterRecycleView extends RecyclerView.Adapter<AdapterRecycleView.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Goroda> gorodalist;



    public AdapterRecycleView(Context context, List<Goroda> goroda){
        this.gorodalist = goroda;
        this.inflater = LayoutInflater.from(context);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView gorod;
        final TextView temper;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gorod = itemView.findViewById(R.id.textViewGorod);
            temper = itemView.findViewById(R.id.textViewTemp);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_gorod, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Goroda gorodaT = gorodalist.get(position);
        holder.gorod.setText(gorodaT.getGorod());
        holder.temper.setText(gorodaT.getTemper());

    }

    @Override
    public int getItemCount() {
        return gorodalist.size();
    }


}

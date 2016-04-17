package com.liutova.avocare.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liutova.avocare.R;

import java.util.ArrayList;

/**
 * Created by Oleksandra Liutova on 17-Apr-16.
 */
public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {

    ArrayList<String> list;

    public FavouritesAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public FavouritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favourite, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FavouritesAdapter.ViewHolder holder, int position) {
        holder.txtView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtView;

        public ViewHolder(View itemView) {
            super(itemView);
            txtView = (TextView) itemView.findViewById(R.id.favourite_name);
        }
    }

}

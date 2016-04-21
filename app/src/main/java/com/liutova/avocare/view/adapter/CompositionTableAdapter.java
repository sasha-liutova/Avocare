package com.liutova.avocare.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liutova.avocare.R;
import com.liutova.avocare.helper.CompositionTableRow;

import java.util.ArrayList;

/**
 * Created by Oleksandra Liutova on 21-Apr-16.
 */
public class CompositionTableAdapter extends RecyclerView.Adapter<CompositionTableAdapter.ViewHolder> {

    ArrayList<CompositionTableRow> itemsData;

    public CompositionTableAdapter(ArrayList<CompositionTableRow> itemsData) {
        this.itemsData = itemsData;
    }

    @Override
    public CompositionTableAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_composition_table, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CompositionTableAdapter.ViewHolder holder, int position) {
        holder.txtViewNumber.setText(itemsData.get(position).getIndex() + "");
        holder.txtViewName.setText(itemsData.get(position).getName());
        holder.txtViewLevel.setText(itemsData.get(position).getSafetyLevel() + "");
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtViewNumber;
        TextView txtViewName;
        TextView txtViewLevel;

        public ViewHolder(View itemView) {
            super(itemView);
            txtViewNumber = (TextView) itemView.findViewById(R.id.compotision_item_number);
            txtViewName = (TextView) itemView.findViewById(R.id.compotision_item_name);
            txtViewLevel = (TextView) itemView.findViewById(R.id.compotision_item_level);
        }
    }
}

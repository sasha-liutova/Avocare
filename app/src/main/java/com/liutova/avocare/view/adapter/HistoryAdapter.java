package com.liutova.avocare.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liutova.avocare.R;
import com.liutova.avocare.helper.HistoryTableRow;

import java.util.ArrayList;

/**
 * Created by Oleksandra Liutova on 21-Apr-16.
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    ArrayList<HistoryTableRow> itemsData;

    public HistoryAdapter(ArrayList<HistoryTableRow> itemsData) {
        this.itemsData = itemsData;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        holder.txtViewName.setText(itemsData.get(position).getName());
        holder.txtViewDate.setText(itemsData.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewName;
        TextView txtViewDate;

        public ViewHolder(View itemView) {
            super(itemView);
            txtViewName = (TextView) itemView.findViewById(R.id.history_item_name);
            txtViewDate = (TextView) itemView.findViewById(R.id.history_item_date);
        }
    }
}

package com.liutova.avocare.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liutova.avocare.R;
import com.liutova.avocare.helper.TypeCompositionItemData;

/**
 * Created by Oleksandra Liutova on 16-Apr-16.
 */
public class TypeCompositionAdapter extends RecyclerView.Adapter<TypeCompositionAdapter.ViewHolder> {

    private TypeCompositionItemData[] itemsData;

    public TypeCompositionAdapter(TypeCompositionItemData[] itemsData) {
        this.itemsData = itemsData;
    }

    @Override
    public TypeCompositionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_type_composition, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TypeCompositionAdapter.ViewHolder holder, int position) {
        holder.txtView.setText(itemsData[position].getName());
    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    // inner class to hold a reference to each item of RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtView = (TextView) itemLayoutView.findViewById(R.id.item_type_composition_name);
        }
    }
}

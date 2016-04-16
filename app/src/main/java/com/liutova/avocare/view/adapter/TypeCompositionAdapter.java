package com.liutova.avocare.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liutova.avocare.R;

import java.util.ArrayList;

/**
 * Created by Oleksandra Liutova on 16-Apr-16.
 */
public class TypeCompositionAdapter extends RecyclerView.Adapter<TypeCompositionAdapter.ViewHolder> {

    private ArrayList<String> itemsData;

    public TypeCompositionAdapter(ArrayList<String> itemsData) {
        this.itemsData = itemsData;
    }

    public void add(String name) {
        itemsData.add(name);
        notifyDataSetChanged();
    }

    public void delete(int index) {
        itemsData.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public TypeCompositionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_type_composition, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, itemsData, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TypeCompositionAdapter.ViewHolder holder, final int position) {
        holder.txtView.setText(itemsData.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtView;
        public ImageView imgView;

        public ViewHolder(View itemLayoutView, final ArrayList<String> itemsData, final TypeCompositionAdapter adapter) {
            super(itemLayoutView);
            txtView = (TextView) itemLayoutView.findViewById(R.id.item_type_composition_name);
            imgView = (ImageView) itemLayoutView.findViewById(R.id.item_type_composition_delete);

            txtView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    itemsData.set(getAdapterPosition(), s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapter.delete(getAdapterPosition());
                }
            });
        }
    }
}

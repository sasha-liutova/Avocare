package com.liutova.avocare.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.liutova.avocare.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by liutoole on 4/18/16.
 */
public class AlergensAdapter extends RecyclerView.Adapter<AlergensAdapter.ViewHolder> {

    ArrayList<String> alergensNamesList;
    ArrayList<String> substancesNamesList;
    WeakReference<Context> mCtx;
    boolean added;

    public AlergensAdapter(Context context, ArrayList<String> alergensNamesList, ArrayList<String> substanceNamesList) {
        this.alergensNamesList = alergensNamesList;
        mCtx = new WeakReference<Context>(context);
        this.substancesNamesList = substancesNamesList;
        added = true;
    }

    public void add(String name) {
        alergensNamesList.add(name);
        notifyDataSetChanged();
        added = true;
    }

    public void delete(int index) {
        alergensNamesList.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public AlergensAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_alergen, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, alergensNamesList, this);
        return null;
    }

    @Override
    public void onBindViewHolder(AlergensAdapter.ViewHolder holder, int position) {
        holder.txtView.setText(alergensNamesList.get(position));
        ArrayAdapter<String> tipsAdapter = new ArrayAdapter<String>(mCtx.get(), R.layout.item_tip, substancesNamesList);
        holder.txtView.setAdapter(tipsAdapter);

        if (position == getItemCount() - 1 && added == true) {
            holder.txtView.requestFocus();
            added = false;
        }

    }

    @Override
    public int getItemCount() {
        return alergensNamesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public AutoCompleteTextView txtView;
        public ImageView imgView;

        public ViewHolder(View itemLayoutView, final ArrayList<String> itemsData, final AlergensAdapter adapter) {
            super(itemLayoutView);
            txtView = (AutoCompleteTextView) itemLayoutView.findViewById(R.id.item_alergen_name);
            imgView = (ImageView) itemLayoutView.findViewById(R.id.item_alergen_delete);

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

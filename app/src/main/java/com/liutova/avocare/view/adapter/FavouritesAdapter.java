package com.liutova.avocare.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liutova.avocare.R;
import com.liutova.avocare.model.MbFavourites;
import com.liutova.avocare.view.activity.BaseActivity;
import com.liutova.avocare.view.fragment.ProductFragment;

import java.util.ArrayList;

/**
 * Created by Oleksandra Liutova on 17-Apr-16.
 */
public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {

    ArrayList<MbFavourites> list;
    BaseActivity baseActivity;

    public FavouritesAdapter(ArrayList<MbFavourites> list, BaseActivity baseActivity) {
        this.list = list;
        this.baseActivity = baseActivity;
    }

    @Override
    public FavouritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favourite, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, baseActivity, this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FavouritesAdapter.ViewHolder holder, int position) {
        holder.txtView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtView;

        public ViewHolder(View itemView, final BaseActivity baseActivity, final FavouritesAdapter adapter) {
            super(itemView);
            txtView = (TextView) itemView.findViewById(R.id.favourite_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    baseActivity.replaceFragment(ProductFragment.newInstance(null, adapter.list.get(getAdapterPosition()).getProductID()));
                }
            });
        }
    }

}

package com.liutova.avocare.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liutova.avocare.R;
import com.liutova.avocare.helper.CompositionTableRow;
import com.liutova.avocare.view.activity.BaseActivity;
import com.liutova.avocare.view.fragment.SubstanceDescriptionDialogFragment;

import java.util.ArrayList;

/**
 * Created by Oleksandra Liutova on 21-Apr-16.
 */
public class CompositionTableAdapter extends RecyclerView.Adapter<CompositionTableAdapter.ViewHolder> {

    ArrayList<CompositionTableRow> itemsData;
    private BaseActivity activity;
    private ArrayList<Integer> alergensIndexesList;

    public CompositionTableAdapter(ArrayList<CompositionTableRow> itemsData, BaseActivity activity, ArrayList<Integer> alergensIndexesList) {
        this.itemsData = itemsData;
        this.activity = activity;
        this.alergensIndexesList = alergensIndexesList;
    }

    @Override
    public CompositionTableAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_composition_table, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CompositionTableAdapter.ViewHolder holder, final int position) {
        holder.txtViewNumber.setText(itemsData.get(position).getIndex() + "");
        holder.txtViewName.setText(itemsData.get(position).getName());
        if(itemsData.get(position).getSafetyLevel() == -10){
            holder.txtViewLevel.setText("");
        } else{
            holder.txtViewLevel.setText(itemsData.get(position).getSafetyLevel() + "");
        }
        if (alergensIndexesList.contains(position)) {
            holder.txtViewLL.setBackgroundColor(activity.getResources().getColor(R.color.red));
        } else {
            holder.txtViewLL.setBackgroundColor(activity.getResources().getColor(R.color.BackgroundColor));
        }
        holder.txtViewLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubstanceDescriptionDialogFragment s = SubstanceDescriptionDialogFragment.newInstance(itemsData.get(position).getName(), itemsData.get(position).getDescription(),
                        itemsData.get(position).getSafetyLevel() + "", itemsData.get(position).getSafetyLevelDescription(), activity);
                s.show(activity.getFragmentManager(), "Substance_description_dialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtViewNumber;
        TextView txtViewName;
        TextView txtViewLevel;
        LinearLayout txtViewLL;

        public ViewHolder(View itemView) {
            super(itemView);
            txtViewNumber = (TextView) itemView.findViewById(R.id.compotision_item_number);
            txtViewName = (TextView) itemView.findViewById(R.id.compotision_item_name);
            txtViewLevel = (TextView) itemView.findViewById(R.id.compotision_item_level);
            txtViewLL = (LinearLayout) itemView.findViewById(R.id.item_composition_table_LL);
        }

    }

}

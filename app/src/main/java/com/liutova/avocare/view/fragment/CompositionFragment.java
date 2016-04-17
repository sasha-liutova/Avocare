package com.liutova.avocare.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liutova.avocare.R;
import com.liutova.avocare.helper.CompositionTableRow;
import com.liutova.avocare.listener.CompositionFragmentListener;
import com.liutova.avocare.network.AsyncTaskComposition;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Oleksandra Liutova on 17-Apr-16.
 */
public class CompositionFragment extends BaseFragment implements CompositionFragmentListener {

    String TAG = this.getClass().getName();
    ArrayList<String> enteredSubstanceNames;

    @Bind(R.id.composition_table)
    LinearLayout compositionTableView;
    @Bind(R.id.blank_layout)
    View blankLayoutView;

    public static CompositionFragment newInstance(ArrayList<String> enteredSubstanceNames) {

        Bundle args = new Bundle();
        args.putStringArrayList("enteredSubstanceNames", enteredSubstanceNames);

        CompositionFragment fragment = new CompositionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_composition;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        enteredSubstanceNames = getArguments().getStringArrayList("enteredSubstanceNames");
        final String languageID = getBaseActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE).getString("LanguageId", "");

        AsyncTaskComposition task = new AsyncTaskComposition(enteredSubstanceNames, languageID, this, getContext());
        task.execute();
        return v;
    }

    @Override
    public void onGetResults(ArrayList<CompositionTableRow> table) {
        // fill composition_table layout
        for (CompositionTableRow item : table) {
            LinearLayout row = new LinearLayout(getBaseActivity());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            row.setOrientation(LinearLayout.HORIZONTAL);

            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            TextView subIndex = new TextView(getBaseActivity());
            subIndex.setLayoutParams(lp2);
            subIndex.setText(item.getIndex() + "");
            row.addView(subIndex);

            TextView subName = new TextView(getBaseActivity());
            subName.setLayoutParams(lp2);
            subName.setText(item.getName());
            row.addView(subName);

            TextView subLevel = new TextView(getBaseActivity());
            subLevel.setLayoutParams(lp2);
            if (item.getSafetyLevel() == -10) {
                subLevel.setText("?");
            } else {
                subLevel.setText(item.getSafetyLevel() + "");
            }
            row.addView(subLevel);

            compositionTableView.addView(row);
        }
        blankLayoutView.setVisibility(View.GONE);
    }
}

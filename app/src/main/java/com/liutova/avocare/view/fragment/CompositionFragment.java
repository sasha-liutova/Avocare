package com.liutova.avocare.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liutova.avocare.R;
import com.liutova.avocare.helper.CompositionTableRow;
import com.liutova.avocare.listener.CompositionFragmentListener;
import com.liutova.avocare.network.AsyncTaskComposition;
import com.liutova.avocare.view.adapter.CompositionTableAdapter;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Oleksandra Liutova on 17-Apr-16.
 */
public class CompositionFragment extends BaseFragment implements CompositionFragmentListener {

    String TAG = this.getClass().getName();
    ArrayList<String> enteredSubstanceNames;

    @Bind(R.id.composition_table)
    RecyclerView compositionTableView;
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

        compositionTableView.setLayoutManager(new LinearLayoutManager(getContext()));
        CompositionTableAdapter adapter = new CompositionTableAdapter(table);
        compositionTableView.setAdapter(adapter);
        compositionTableView.setItemAnimator(new DefaultItemAnimator());

        blankLayoutView.setVisibility(View.GONE);
    }
}

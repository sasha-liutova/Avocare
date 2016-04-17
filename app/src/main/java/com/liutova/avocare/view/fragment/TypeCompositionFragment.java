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
import com.liutova.avocare.helper.Helper;
import com.liutova.avocare.listener.TypeCompositionFragmentListener;
import com.liutova.avocare.network.AsyncTaskTypeComposition;
import com.liutova.avocare.view.adapter.TypeCompositionAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Oleksandra Liutova on 14-Apr-16.
 */
public class TypeCompositionFragment extends BaseFragment implements TypeCompositionFragmentListener {

    @Bind(R.id.composition_listview)
    RecyclerView compositionRecyclerView;
    TypeCompositionAdapter adapter;
    private ArrayList<String> substanceNamesList;
    private String TAG = this.getClass().getName();
    private String languageID;

    public static TypeCompositionFragment newInstance() {

        Bundle args = new Bundle();

        TypeCompositionFragment fragment = new TypeCompositionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_type_composition;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        substanceNamesList = new ArrayList<String>();
        languageID = getBaseActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE).getString("LanguageId", "");
        AsyncTaskTypeComposition task = new AsyncTaskTypeComposition(languageID, this);
        task.execute();

        compositionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<String> list = new ArrayList<String>();
        list.add("");
        adapter = new TypeCompositionAdapter(getBaseActivity(), list, substanceNamesList);
        compositionRecyclerView.setAdapter(adapter);
        compositionRecyclerView.setItemAnimator(new DefaultItemAnimator());
        Helper.showKeyboard(getBaseActivity());

        return v;
    }

    @OnClick(R.id.add_button)
    public void onClickAddButton() {
        adapter.add("");
        Helper.showKeyboard(getBaseActivity());
    }

    @Override
    public void onGetResults(ArrayList<String> list) {
        for (String name : list) {
            substanceNamesList.add(name);
        }
    }
}

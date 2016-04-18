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
import com.liutova.avocare.network.AsyncTaskTypeComposition;
import com.liutova.avocare.view.adapter.TypeCompositionAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by liutoole on 4/18/16.
 */
public class AlergensFragment extends BaseFragment {

    @Bind(R.id.alergens_listview)
    RecyclerView alergensView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_alergens;
    }

    public static AlergensFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AlergensFragment fragment = new AlergensFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        ArrayList<String> alergensList = new ArrayList<String>();

        alergensView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TypeCompositionAdapter(getBaseActivity(), alergensList);
        alergensView.setAdapter(adapter);
        alergensView.setItemAnimator(new DefaultItemAnimator());

        return v;
    }

    @OnClick(R.id.add_alergen_btn)
    public void onAddAlergen(){

    }
}

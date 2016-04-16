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

        languageID = getBaseActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE).getString("LanguageId", "");
        AsyncTaskTypeComposition task = new AsyncTaskTypeComposition(languageID, this);
        task.execute();

//        TypeCompositionItemData itemsData[] = {new TypeCompositionItemData("Water"),
//                new TypeCompositionItemData("Something"),
//                new TypeCompositionItemData("Beer"),
//                new TypeCompositionItemData("Rose water"),
//                new TypeCompositionItemData("Glycerin"),
//                new TypeCompositionItemData("Palmitate")};

        String itemsData[] = {"Water", "Something", "Beer", "Rose water", "Glycerin", "Palmitate"};

        compositionRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        TypeCompositionAdapter mAdapter = new TypeCompositionAdapter(itemsData);
        compositionRecyclerView.setAdapter(mAdapter);
        compositionRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return v;
    }

    @OnClick(R.id.add_button)
    public void onClickAddButton() {

    }

    @Override
    public void onGetResults(ArrayList<String> list) {
        substanceNamesList = list;
    }
}

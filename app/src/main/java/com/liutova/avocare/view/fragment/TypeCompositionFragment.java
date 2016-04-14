package com.liutova.avocare.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liutova.avocare.R;
import com.liutova.avocare.listener.TypeCompositionFragmentListener;
import com.liutova.avocare.network.AsyncTaskTypeComposition;

import java.util.ArrayList;

/**
 * Created by Oleksandra Liutova on 14-Apr-16.
 */
public class TypeCompositionFragment extends BaseFragment implements TypeCompositionFragmentListener {

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

        return v;
    }

    @Override
    public void onGetResults(ArrayList<String> list) {
        substanceNamesList = list;
    }
}

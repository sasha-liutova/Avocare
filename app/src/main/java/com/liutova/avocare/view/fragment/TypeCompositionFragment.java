package com.liutova.avocare.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liutova.avocare.R;

/**
 * Created by Oleksandra Liutova on 14-Apr-16.
 */
public class TypeCompositionFragment extends BaseFragment {
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
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

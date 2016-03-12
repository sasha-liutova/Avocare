package com.liutova.avocare.view;

import android.os.Bundle;

import com.liutova.avocare.R;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
public class MainFragment extends BaseFragment {

    public static MainFragment newInstance() {

        Bundle args = new Bundle();

        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }
}

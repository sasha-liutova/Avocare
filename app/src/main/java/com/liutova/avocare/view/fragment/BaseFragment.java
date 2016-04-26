package com.liutova.avocare.view.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liutova.avocare.view.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
public abstract class BaseFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(),container,false);

        ButterKnife.bind(this, view);

        return view;
    }

    protected abstract int getLayout();

    public BaseActivity getBaseActivity() {
        Activity activity = getActivity();
        if (activity != null) {
            return (BaseActivity) activity;
        }
        return null;
    }

}

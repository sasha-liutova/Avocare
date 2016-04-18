package com.liutova.avocare.view.fragment;

import android.os.Bundle;

import com.liutova.avocare.R;

/**
 * Created by liutoole on 4/18/16.
 */
public class AboutFragment extends BaseFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_about;
    }

    public static AboutFragment newInstance() {

        Bundle args = new Bundle();

        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

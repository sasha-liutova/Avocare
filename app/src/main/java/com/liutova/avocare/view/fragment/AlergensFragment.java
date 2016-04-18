package com.liutova.avocare.view.fragment;

import android.os.Bundle;

import com.liutova.avocare.R;

/**
 * Created by liutoole on 4/18/16.
 */
public class AlergensFragment extends BaseFragment {
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
}

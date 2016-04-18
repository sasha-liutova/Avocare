package com.liutova.avocare.view.fragment;

import android.os.Bundle;

import com.liutova.avocare.R;

/**
 * Created by liutoole on 4/18/16.
 */
public class HelpFragment extends BaseFragment {
    @Override
    protected int getLayout() {
        return R.layout.fragment_help;
    }

    public static HelpFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HelpFragment fragment = new HelpFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

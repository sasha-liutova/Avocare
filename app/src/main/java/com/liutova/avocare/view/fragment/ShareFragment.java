package com.liutova.avocare.view.fragment;

import android.os.Bundle;

import com.liutova.avocare.R;

/**
 * Created by liutoole on 4/18/16.
 */
public class ShareFragment extends BaseFragment{
    @Override
    protected int getLayout() {
        return R.layout.fragment_share;
    }

    public static ShareFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ShareFragment fragment = new ShareFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

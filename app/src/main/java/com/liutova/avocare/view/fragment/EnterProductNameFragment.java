package com.liutova.avocare.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liutova.avocare.R;

import butterknife.Bind;

/**
 * Created by Oleksandra Liutova on 09-Apr-16.
 */
public class EnterProductNameFragment extends BaseFragment {

    @Bind(R.id.found_products_listview)
    ListView productsListView;

    public static EnterProductNameFragment newInstance() {

        Bundle args = new Bundle();

        EnterProductNameFragment fragment = new EnterProductNameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_enter_product_name;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        String[] mobileArray = {"Android", "IPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X"};

        ArrayAdapter adapter = new ArrayAdapter<String>(getBaseActivity(), R.layout.item_product_name, R.id.item_product_name_textview, mobileArray);

        productsListView.setAdapter(adapter);

        return v;
    }
}

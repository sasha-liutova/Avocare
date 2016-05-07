package com.liutova.avocare.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liutova.avocare.R;
import com.liutova.avocare.view.activity.BarcodeScannerActivity;

import butterknife.OnClick;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        getBaseActivity().changeTitle(getBaseActivity().getString(R.string.app_name));
        return v;
    }

    @OnClick(R.id.main_menu_btn_barcode)
    public void onClickMainMenuBtnBarcode(View view) {
        getBaseActivity().startActivity(new Intent(getBaseActivity(), BarcodeScannerActivity.class));
    }

    @OnClick(R.id.main_menu_btn_product_name)
    public void onClickMainMenuEnterProductName(View view) {
        getBaseActivity().replaceFragment(EnterProductNameFragment.newInstance());
    }

    @OnClick(R.id.main_menu_btn_composition)
    public void onClickMainMenuTypeComposition(View view) {
        getBaseActivity().replaceFragment(TypeCompositionFragment.newInstance());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseActivity().getmNavigationView().getMenu().getItem(0).setChecked(true);
    }
}

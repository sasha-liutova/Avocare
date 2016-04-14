package com.liutova.avocare.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
}

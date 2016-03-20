package com.liutova.avocare.view.activity;

import android.os.Bundle;

import com.liutova.avocare.view.fragment.MainFragment;
import com.liutova.avocare.view.fragment.ProductFragment;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(MainFragment.newInstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent() != null && getIntent().getExtras() != null) {
            String barcodeValue = getIntent().getStringExtra(BarcodeScannerActivity.TAG_BARCODE);
            replaceFragment(ProductFragment.newInstance(barcodeValue));
        }
    }
}

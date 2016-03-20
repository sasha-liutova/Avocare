package com.liutova.avocare.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.liutova.avocare.view.fragment.MainFragment;
import com.liutova.avocare.view.fragment.ProductFragment;

import java.util.Locale;


public class MainActivity extends BaseActivity {

    private String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSharedPreferences("preferences", MODE_PRIVATE).getString("language", null) == null) {
            String newLanguage = Locale.getDefault().getLanguage();
            SharedPreferences.Editor editor = getSharedPreferences("preferences", MODE_PRIVATE).edit();
            editor.putString("language", newLanguage);
            editor.commit();
            Log.d(TAG, "onCreate: language newly set to " + newLanguage);
        } else {
            Log.d(TAG, "onCreate: language is already set to " + getSharedPreferences("preferences", MODE_PRIVATE).getString("language", ""));
        }
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

package com.liutova.avocare.view.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.liutova.avocare.helper.Helper;
import com.liutova.avocare.network.AsyncTaskLanguageID;
import com.liutova.avocare.view.fragment.MainFragment;
import com.liutova.avocare.view.fragment.ProductFragment;

import java.util.Locale;

public class MainActivity extends BaseActivity {

    private String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSharedPreferences("preferences", MODE_PRIVATE).getString("languageCode", null) == null) {
            String newLanguageCode = Locale.getDefault().getLanguage();
            if (!newLanguageCode.equals("en") && !newLanguageCode.equals("cs")) {
                newLanguageCode = "en";
            }
            SharedPreferences.Editor editor = getSharedPreferences("preferences", MODE_PRIVATE).edit();
            editor.putString("languageCode", newLanguageCode);
            editor.commit();
            AsyncTaskLanguageID task = new AsyncTaskLanguageID(getApplicationContext());
            task.execute();
            getResources().updateConfiguration(Helper.getConfigurationFromPreferences(newLanguageCode), getResources().getDisplayMetrics());
        }
        replaceFragment(MainFragment.newInstance());
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent() != null && getIntent().getExtras() != null) {
            String barcodeValue = getIntent().getStringExtra(BarcodeScannerActivity.TAG_BARCODE);
            replaceFragment(ProductFragment.newInstance(barcodeValue, null));
        }
    }

}

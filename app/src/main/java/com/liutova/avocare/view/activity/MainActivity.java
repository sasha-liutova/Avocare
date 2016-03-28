package com.liutova.avocare.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.liutova.avocare.network.AsyncTaskLanguageID;
import com.liutova.avocare.view.fragment.MainFragment;
import com.liutova.avocare.view.fragment.ProductFragment;

import java.util.Locale;

//import com.liutova.avocare.network.AsyncTaskLanguageID;


public class MainActivity extends BaseActivity {

    private String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSharedPreferences("preferences", MODE_PRIVATE).getString("languageCode", null) == null) {
            String newLanguageCode = Locale.getDefault().getLanguage();
            // only 4 languages available
            if (!newLanguageCode.equals("en") && !newLanguageCode.equals("ru") && !newLanguageCode.equals("cs") && !newLanguageCode.equals("ua")) {
                newLanguageCode = "en";
            }
            SharedPreferences.Editor editor = getSharedPreferences("preferences", MODE_PRIVATE).edit();
            editor.putString("languageCode", newLanguageCode);
            editor.commit();
            Log.d(TAG, "onCreate: language newly set to " + newLanguageCode);
            AsyncTaskLanguageID task = new AsyncTaskLanguageID(getApplicationContext());
            task.execute();
        } else {
            Log.d(TAG, "onCreate: language is already set to " + getSharedPreferences("preferences", MODE_PRIVATE).getString("languageCode", ""));
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

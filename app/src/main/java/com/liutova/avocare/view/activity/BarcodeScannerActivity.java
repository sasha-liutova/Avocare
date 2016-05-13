package com.liutova.avocare.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Oleksandra Liutova on 19-Mar-16.
 */
public class BarcodeScannerActivity extends Activity implements ZXingScannerView.ResultHandler {
    public static final String TAG_BARCODE = "Barcode value";
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(TAG_BARCODE, rawResult.getText());
        startActivity(intent);
        finish();

    }
}
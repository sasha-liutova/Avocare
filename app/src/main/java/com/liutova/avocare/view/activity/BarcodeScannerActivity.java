package com.liutova.avocare.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Oleksandra Liutova on 19-Mar-16.
 */
public class BarcodeScannerActivity extends Activity implements ZXingScannerView.ResultHandler {
    public static final String TAG_BARCODE = "Barcode value";
    public static final String TAG = BarcodeScannerActivity.class.getName();
    private ZXingScannerView mScannerView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.d(TAG, rawResult.getText()); // Prints scan results
        Log.d(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(TAG_BARCODE, rawResult.getText());
        startActivity(intent);
        finish();

//        Bundle bundle = new Bundle();
//        bundle.put("",rawResult.getText());


        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }
}
package com.liutova.avocare.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.liutova.avocare.R;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Oleksandra Liutova on 13-Mar-16.
 */
public class BarcodeReaderFragment extends BaseFragment {

    @Bind(R.id.imgview)
    ImageView myImageView;
    @Bind(R.id.txtContent)
    TextView txtView;

    public static BarcodeReaderFragment newInstance() {

        Bundle args = new Bundle();

        BarcodeReaderFragment fragment = new BarcodeReaderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_barcode_reader;
    }


    @OnClick(R.id.process_button)
    public void onClickProcessBtn(View view) {
        // process image into bitmap
        Bitmap myBitmap = BitmapFactory.decodeResource(
                getBaseActivity().getApplicationContext().getResources(),
                R.drawable.puppy);
        myImageView.setImageBitmap(myBitmap);

        // create barcode detector
        BarcodeDetector detector =
                new BarcodeDetector.Builder(getBaseActivity().getApplicationContext())
                        .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                        .build();
        if (!detector.isOperational()) {
            txtView.setText("Could not set up the detector!");
            return;
        }

        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);

        Barcode thisCode = barcodes.valueAt(0);
        TextView txtView = (TextView) getBaseActivity().findViewById(R.id.txtContent);
        txtView.setText(thisCode.rawValue);
    }
}

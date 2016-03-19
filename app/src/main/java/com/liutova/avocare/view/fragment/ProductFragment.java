package com.liutova.avocare.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liutova.avocare.R;
import com.liutova.avocare.view.activity.BarcodeScannerActivity;

import butterknife.Bind;

/**
 * Created by Oleksandra Liutova on 19-Mar-16.
 */
public class ProductFragment extends BaseFragment {

    @Bind(R.id.barcodeValue)
    TextView barcodeValueTextView;

    String barcode;

    public static ProductFragment newInstance(String barcodeValue) {

        Bundle args = new Bundle();
        args.putString(BarcodeScannerActivity.TAG_BARCODE, barcodeValue);
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        barcode = getArguments().getString(BarcodeScannerActivity.TAG_BARCODE, "");
        barcodeValueTextView.setText(barcode);

        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_product;
    }
}

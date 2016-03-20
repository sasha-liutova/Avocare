package com.liutova.avocare.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liutova.avocare.R;
import com.liutova.avocare.model.DbProductBarcode;
import com.liutova.avocare.model.DbProductDescription;
import com.liutova.avocare.view.activity.BarcodeScannerActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

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

        // get system language - TO DO
//        String languageCode = "en";
//        ParseQuery<DbLanguage> query = DbLanguage.getQuery();
//        query.whereEqualTo("code", languageCode);
//        query.findInBackground(new FindCallback<DbLanguage>() {
//            @Override
//            public void done(List<DbLanguage> objects, ParseException e) {
//                if (e == null) {
//                    if(objects.size()==0){
//                        barcodeValueTextView.setText("Error occured in language search.");
//
//                    }
//                    else{
//
//                    }
//                }
//                else {
//                    barcodeValueTextView.setText("Error occured in language search.");
//                }
//            }
//        };

        ParseQuery<DbProductBarcode> query = DbProductBarcode.getQuery();
        query.whereEqualTo("barcode", barcode);
        query.findInBackground(new FindCallback<DbProductBarcode>() {
            @Override
            public void done(List<DbProductBarcode> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() == 0) {
                        barcodeValueTextView.setText("Product not found.");
                    } else {
                        ParseQuery<DbProductDescription> query = DbProductDescription.getQuery();
                        query.whereEqualTo("productID", objects.get(0).getProductID());
                        query.whereEqualTo("languageID", "OzCyXIQ5LT");
                        query.findInBackground(new FindCallback<DbProductDescription>() {
                            @Override
                            public void done(List<DbProductDescription> objects, ParseException e) {
                                if (e == null) {
                                    if (objects.size() == 0) {
                                        barcodeValueTextView.setText("Product not found.");
                                    } else {
                                        barcodeValueTextView.setText(objects.get(0).getName());
                                    }
                                } else {
                                    barcodeValueTextView.setText("Error occured.");
                                }
                            }
                        });
                    }
                } else {
                    barcodeValueTextView.setText("Error occured.");
                }
            }
        });
//            public void done(ParseObject object, ParseException e) {
//                if (e == null) {
//                    // object will be your game score
//                } else {
//                    barcodeValueTextView.setText("Product not found.");
//                }
//            }
//        });

        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_product;
    }
}

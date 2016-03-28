package com.liutova.avocare.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liutova.avocare.R;
import com.liutova.avocare.model.DbProduct;
import com.liutova.avocare.model.DbProductBarcode;
import com.liutova.avocare.model.DbProductDescription;
import com.liutova.avocare.model.DbSafetyLevel;
import com.liutova.avocare.model.DbSafetyLevelDescription;
import com.liutova.avocare.view.activity.BarcodeScannerActivity;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Oleksandra Liutova on 19-Mar-16.
 */
public class ProductFragment extends BaseFragment {

    @Bind(R.id.productName)
    TextView productNameTextView;
    @Bind(R.id.safetyLevel)
    TextView safetyLevelTextView;

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
        final String languageID = "OzCyXIQ5LT";
        //getSharedPreferences("preferences", MODE_PRIVATE).getString("languageCode", "");

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
                        productNameTextView.setText("Product not found.");
                    } else {
                        String productID = objects.get(0).getProductID();
                        // get product name in ProductDescription
                        ParseQuery<DbProductDescription> query = DbProductDescription.getQuery();
                        query.whereEqualTo("productID", productID);
                        query.whereEqualTo("languageID", languageID);
                        query.findInBackground(new FindCallback<DbProductDescription>() {
                            @Override
                            public void done(List<DbProductDescription> objects, ParseException e) {
                                if (e == null) {
                                    if (objects.size() == 0) {
                                        productNameTextView.setText("Product not found.");
                                    } else {
                                        String productName = objects.get(0).getName();
                                        productNameTextView.setText(productName);
                                    }
                                } else {
                                    productNameTextView.setText("Error occured.");
                                }
                            }
                        });

                        // get product safety level ID and photo in Product
                        ParseQuery<DbProduct> query2 = DbProduct.getQuery();
                        query2.whereEqualTo("objectId", productID);
                        query2.findInBackground(new FindCallback<DbProduct>() {
                            @Override
                            public void done(List<DbProduct> objects, ParseException e) {
                                if (e == null) {
                                    if (objects.size() == 0) {
                                        safetyLevelTextView.setText("Product not found.");
                                    } else {
                                        // set photo - TO DO
                                        ParseFile photoFile = objects.get(0).getPhoto();

                                        // get safety level in SafetyLevel
                                        final String safetyLevelID = objects.get(0).getSafetyLevelID();
                                        ParseQuery<DbSafetyLevel> query = DbSafetyLevel.getQuery();
                                        query.whereEqualTo("objectId", safetyLevelID);
                                        query.findInBackground(new FindCallback<DbSafetyLevel>() {
                                            @Override
                                            public void done(List<DbSafetyLevel> objects, ParseException e) {
                                                if (e == null) {
                                                    if (objects.size() == 0) {
                                                        safetyLevelTextView.setText("Product not found.");
                                                    } else {

                                                        // get safety level description
                                                        final String safetyLevel = objects.get(0).getLevel();
                                                        ParseQuery<DbSafetyLevelDescription> query = DbSafetyLevelDescription.getQuery();
                                                        query.whereEqualTo("safetyLevelID", safetyLevelID);
                                                        query.whereEqualTo("languageID", languageID);
                                                        query.findInBackground(new FindCallback<DbSafetyLevelDescription>() {
                                                            @Override
                                                            public void done(List<DbSafetyLevelDescription> objects, ParseException e) {
                                                                if (e == null) {
                                                                    if (objects.size() == 0) {
                                                                        safetyLevelTextView.setText("Product not found.");
                                                                    } else {
                                                                        String safetyLevelDescription = objects.get(0).getDescription();
                                                                        // TO DO - extract string "General safety level"
                                                                        String finalSafetyDescription = "General safety level" + ": " + safetyLevel + "(" + safetyLevelDescription + ")";
                                                                        safetyLevelTextView.setText(finalSafetyDescription);
                                                                    }
                                                                } else {
                                                                    safetyLevelTextView.setText("Error occured.");
                                                                }
                                                            }
                                                        });

                                                    }
                                                } else {
                                                    safetyLevelTextView.setText("Error occured.");
                                                }
                                            }
                                        });

                                    }
                                } else {
                                    safetyLevelTextView.setText("Error occured.");
                                }
                            }
                        });

                    }
                } else {
                    productNameTextView.setText("Error occured.");
                }
            }
        });

        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_product;
    }
}

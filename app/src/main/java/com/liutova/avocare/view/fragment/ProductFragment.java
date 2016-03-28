package com.liutova.avocare.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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

    String TAG = this.getClass().getName();

    String barcode;
    String productID;
    String productName;
    String safetyLevelID;
    ParseFile photoFile;
    String safetyLevel;
    String safetyLevelDescription;

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

        // get productID by barcode in ProductBarcode
        ParseQuery<DbProductBarcode> query = DbProductBarcode.getQuery();
        query.whereEqualTo("barcode", barcode);
        query.findInBackground(new FindCallback<DbProductBarcode>() {
            @Override
            public void done(List<DbProductBarcode> objects, ParseException e) {
                if (e == null) {
                    if (objects.size() == 0) {
                        productNameTextView.setText("Product not found.");
                    } else {
                        productID = objects.get(0).getProductID();
                        Log.d(TAG, "product id : " + productID);
                    }
                } else {
                    productNameTextView.setText("Error occured.");
                }
            }
        });

        Log.d(TAG, "product id after: " + productID);

        if (productID != null) {
            Log.d(TAG, " if(productID != null)");
            // get product name in ProductDescription
            ParseQuery<DbProductDescription> query2 = DbProductDescription.getQuery();
            query2.whereEqualTo("productID", productID);
            query2.whereEqualTo("languageID", languageID);
            query2.findInBackground(new FindCallback<DbProductDescription>() {
                @Override
                public void done(List<DbProductDescription> objects, ParseException e) {
                    if (e == null) {
                        if (objects.size() == 0) {
                            productNameTextView.setText("Product not found.");
                        } else {
                            productName = objects.get(0).getName();
                            Log.d(TAG, "productName : " + productName);
                            productNameTextView.setText(productName);
                        }
                    } else {
                        productNameTextView.setText("Error occured.");
                    }
                }
            });

            // get product safety level ID and photo in Product
            ParseQuery<DbProduct> query3 = DbProduct.getQuery();
            query3.whereEqualTo("objectId", productID);
            query3.findInBackground(new FindCallback<DbProduct>() {
                @Override
                public void done(List<DbProduct> objects, ParseException e) {
                    if (e == null) {
                        if (objects.size() == 0) {
                            safetyLevelTextView.setText("Product not found.");
                        } else {
                            safetyLevelID = objects.get(0).getSafetyLevelID();
                            Log.d(TAG, "safetyLevelID : " + safetyLevelID);
                            photoFile = objects.get(0).getPhoto();
                        }
                    } else {
                        safetyLevelTextView.setText("Error occured.");
                    }
                }
            });
        }

        if (safetyLevelID != null) {
            Log.d(TAG, " if(safetyLevelID != null)");
            //TO DO - set photo

            // get safety level in SafetyLevel
            ParseQuery<DbSafetyLevel> query4 = DbSafetyLevel.getQuery();
            query4.whereEqualTo("objectId", safetyLevelID);
            query4.findInBackground(new FindCallback<DbSafetyLevel>() {
                @Override
                public void done(List<DbSafetyLevel> objects, ParseException e) {
                    if (e == null) {
                        if (objects.size() == 0) {
                            safetyLevelTextView.setText("Product not found.");
                        } else {
                            safetyLevel = objects.get(0).getLevel();
                            Log.d(TAG, "safetyLevel : " + safetyLevel);
                        }
                    } else {
                        safetyLevelTextView.setText("Error occured.");
                    }
                }
            });

            // get safety level description
            ParseQuery<DbSafetyLevelDescription> query5 = DbSafetyLevelDescription.getQuery();
            query5.whereEqualTo("safetyLevelID", safetyLevelID);
            query5.whereEqualTo("languageID", languageID);
            query5.findInBackground(new FindCallback<DbSafetyLevelDescription>() {
                @Override
                public void done(List<DbSafetyLevelDescription> objects, ParseException e) {
                    if (e == null) {
                        if (objects.size() == 0) {
                            safetyLevelTextView.setText("Product not found.");
                        } else {
                            safetyLevelDescription = objects.get(0).getDescription();
                            Log.d(TAG, "safetyLevelDescription : " + safetyLevelDescription);
                        }
                    } else {
                        safetyLevelTextView.setText("Error occured.");
                    }
                }
            });
        }

        if (safetyLevel != null && safetyLevelDescription != null) {
            // TO DO - extract string "General safety level"
            String finalSafetyDescription = "General safety level" + ": " + safetyLevel + "(" + safetyLevelDescription + ")";
            safetyLevelTextView.setText(finalSafetyDescription);
        }

        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_product;
    }
}

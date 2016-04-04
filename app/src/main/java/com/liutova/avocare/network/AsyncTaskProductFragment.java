package com.liutova.avocare.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.liutova.avocare.helper.CompositionTableRow;
import com.liutova.avocare.listener.ProductFragmentListener;
import com.liutova.avocare.model.DbProduct;
import com.liutova.avocare.model.DbProductBarcode;
import com.liutova.avocare.model.DbProductDescription;
import com.liutova.avocare.model.DbProductsToSubstances;
import com.liutova.avocare.model.DbSafetyLevel;
import com.liutova.avocare.model.DbSafetyLevelDescription;
import com.liutova.avocare.model.DbSubstance;
import com.liutova.avocare.model.DbSubstanceDescription;
import com.liutova.avocare.model.DbSubstanceName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksandra Liutova on 28-Mar-16.
 */
public class AsyncTaskProductFragment extends AsyncTask {

    String productID;
    String productName;
    String safetyLevelID;
    ParseFile photoFile;
    int safetyLevel;
    String safetyLevelDescription;
    boolean isFavourite;
    Context context;
    String photoURL;
    ArrayList<CompositionTableRow> table;

    String languageID;
    String barcode;
    ProductFragmentListener listener;
    String TAG = this.getClass().getName();

    public AsyncTaskProductFragment(String languageID, String barcode, ProductFragmentListener listener, Context context) {
        this.languageID = languageID;
        this.barcode = barcode;
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        // get productID by barcode in ProductBarcode
        ParseQuery<DbProductBarcode> query = DbProductBarcode.getQuery();
        query.whereEqualTo("barcode", barcode);
        List<DbProductBarcode> objects = null;
        try {
            objects = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (objects != null && objects.size() > 0) {
            productID = objects.get(0).getProductID();
            Log.d(TAG, "product id : " + productID);
        }

        if (productID != null) {

            // get product name in ProductDescription
            ParseQuery<DbProductDescription> query2 = DbProductDescription.getQuery();
            query2.whereEqualTo("productID", productID);
            query2.whereEqualTo("languageID", languageID);
            List<DbProductDescription> objects2 = null;
            try {
                objects2 = query2.find();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (objects2 != null) {
                productName = objects2.get(0).getName();
                Log.d(TAG, "productName : " + productName);
            }

            // get product safety level ID and photo in Product
            ParseQuery<DbProduct> query3 = DbProduct.getQuery();
            query3.whereEqualTo("objectId", productID);
            List<DbProduct> objects3 = null;
            try {
                objects3 = query3.find();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (objects3 != null) {
                safetyLevelID = objects3.get(0).getSafetyLevelID();
                Log.d(TAG, "safetyLevelID : " + safetyLevelID);
                photoFile = objects3.get(0).getPhoto();
                photoURL = photoFile.getUrl();
            }

            // get safety level in SafetyLevel
            ParseQuery<DbSafetyLevel> query4 = DbSafetyLevel.getQuery();
            query4.whereEqualTo("objectId", safetyLevelID);
            List<DbSafetyLevel> objects4 = null;
            try {
                objects4 = query4.find();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (objects4 != null) {
                safetyLevel = objects4.get(0).getLevel();
                Log.d(TAG, "safetyLevel : " + safetyLevel);
            }

            // get safety level description
            ParseQuery<DbSafetyLevelDescription> query5 = DbSafetyLevelDescription.getQuery();
            query5.whereEqualTo("safetyLevelID", safetyLevelID);
            query5.whereEqualTo("languageID", languageID);
            List<DbSafetyLevelDescription> objects5 = null;
            try {
                objects5 = query5.find();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (objects5 != null) {
                safetyLevelDescription = objects5.get(0).getDescription();
                Log.d(TAG, "safetyLevelDescription : " + safetyLevelDescription);
            }

            // pull data for composition
            // get substance IDs in ProductsTOSUbstances
            ParseQuery<DbProductsToSubstances> query6 = DbProductsToSubstances.getQuery();
            query6.whereEqualTo("productID", productID);
            List<DbProductsToSubstances> objects6 = null;
            try {
                objects6 = query6.find();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            table = new ArrayList<>();
            if (objects6 != null && objects6.size() > 0) {
                for (DbProductsToSubstances record : objects6) {
                    table.add(new CompositionTableRow(record.getOrderInComposition(), record.getSubstanceID()));
                }

                // for each substance id find safety level id
                for (CompositionTableRow item : table) {

                    ParseQuery<DbSubstance> query7 = DbSubstance.getQuery();
                    query5.whereEqualTo("objectId", item.getId());
                    List<DbSubstance> objects7 = null;
                    try {
                        objects7 = query7.find();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (objects7 != null && objects7.size() > 0) {
                        item.setSafetyLevelID(objects7.get(0).getSafetyLevelID());
                    }

                    // for each substance find safety level value
                    ParseQuery<DbSafetyLevel> query8 = DbSafetyLevel.getQuery();
                    query4.whereEqualTo("objectId", item.getSafetyLevelID());
                    List<DbSafetyLevel> objects8 = null;
                    try {
                        objects8 = query8.find();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (objects8 != null && objects8.size() > 0) {
                        item.setSafetyLevel(objects8.get(0).getLevel());
                    }

                    // for each substance find safety level description
                    ParseQuery<DbSafetyLevelDescription> query9 = DbSafetyLevelDescription.getQuery();
                    query5.whereEqualTo("safetyLevelID", item.getSafetyLevelID());
                    query5.whereEqualTo("languageID", languageID);
                    List<DbSafetyLevelDescription> objects9 = null;
                    try {
                        objects9 = query9.find();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (objects9 != null && objects9.size() > 0) {
                        item.setSafetyLevelDescription(objects9.get(0).getDescription());
                    }

                    // for each substance find description
                    ParseQuery<DbSubstanceDescription> query10 = DbSubstanceDescription.getQuery();
                    query5.whereEqualTo("substanceID", item.getId());
                    query5.whereEqualTo("languageID", languageID);
                    List<DbSubstanceDescription> objects10 = null;
                    try {
                        objects10 = query10.find();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (objects10 != null && objects10.size() > 0) {
                        item.setDescription(objects10.get(0).getDescription());
                    }

                    // for each substance find name
                    ParseQuery<DbSubstanceName> query11 = DbSubstanceName.getQuery();
                    query11.whereEqualTo("substanceID", item.getId());
                    query11.whereEqualTo("languageID", languageID);
                    List<DbSubstanceName> objects11 = null;
                    try {
                        objects11 = query11.find();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (objects11 != null && objects11.size() > 0) {
                        item.setName(objects11.get(0).getName());
                    }
                }

                //TODO sort table

            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (listener != null) {
            listener.onGetResults(productName, safetyLevel, safetyLevelDescription, photoURL, productID, table);
        }
    }
}

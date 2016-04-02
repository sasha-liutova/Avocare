package com.liutova.avocare.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.liutova.avocare.listener.ProductFragmentListener;
import com.liutova.avocare.model.DbProduct;
import com.liutova.avocare.model.DbProductBarcode;
import com.liutova.avocare.model.DbProductDescription;
import com.liutova.avocare.model.DbSafetyLevel;
import com.liutova.avocare.model.DbSafetyLevelDescription;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;

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
        if (objects != null) {
            productID = objects.get(0).getProductID();
            Log.d(TAG, "product id : " + productID);
        }

        if (productID != null) {

            // search for product ID in local DB if is favourite
//            isFavourite = false;
//            Realm realm = null;
//            RealmConfiguration realmConfig = new RealmConfiguration.Builder(AvocareApplication.getAppContext()).build();
//            realm = Realm.getInstance(realmConfig);
//            RealmResults<MbFavourites> favourites = realm.where(MbFavourites.class).equalTo("productID", productID).findAll();
//            if(favourites.size() >0){
//                isFavourite=true;
//            }
//            realm.close();

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
            }
        }

        if (safetyLevelID != null) {

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
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (listener != null) {
            listener.onGetResults(productName, safetyLevel, safetyLevelDescription, photoFile.getUrl(), productID);
        }
    }
}

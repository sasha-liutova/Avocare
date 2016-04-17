package com.liutova.avocare.network;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import com.liutova.avocare.model.DbLanguage;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Oleksandra Liutova on 20-Mar-16.
 */
public class AsyncTaskLanguageID extends AsyncTask {

    String TAG = this.getClass().getName();

    WeakReference<Context> mC;
    String newLanguageId;

    public AsyncTaskLanguageID(Context mC) {
        this.mC = new WeakReference<Context>(mC);
    }

    @Override
    protected Object doInBackground(Object[] params) {

        if (mC.get() != null) {

            String languageCode = mC.get().getSharedPreferences("preferences", Context.MODE_PRIVATE).getString("languageCode", "");

            ParseQuery<DbLanguage> query = DbLanguage.getQuery();
            query.whereEqualTo("code", languageCode);
            List<DbLanguage> objects = null;
            try {
                objects = query.find();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            newLanguageId = objects.get(0).getObjectId();
            Log.d(TAG, "Languages: new lang id: " + newLanguageId);
            SharedPreferences.Editor editor = mC.get().getSharedPreferences("preferences", Context.MODE_PRIVATE).edit();
            editor.putString("LanguageId", newLanguageId);
            editor.commit();
        }

        return null;
    }
}

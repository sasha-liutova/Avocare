package com.liutova.avocare.network;

import android.os.AsyncTask;

import com.liutova.avocare.listener.TypeCompositionFragmentListener;
import com.liutova.avocare.model.DbSubstanceName;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksandra Liutova on 14-Apr-16.
 */
public class AsyncTaskTypeComposition extends AsyncTask {

    ArrayList<String> list;
    TypeCompositionFragmentListener listener;
    private String languageID;

    public AsyncTaskTypeComposition(String languageID, TypeCompositionFragmentListener listener) {
        this.languageID = languageID;
        this.listener = listener;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        list = new ArrayList<String>();
        ParseQuery<DbSubstanceName> query = DbSubstanceName.getQuery();
        query.whereEqualTo("languageID", languageID);
        List<DbSubstanceName> objects = null;
        try {
            objects = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (objects != null && objects.size() > 0) {
            for (DbSubstanceName item : objects) {
                list.add(item.getName());
            }
        }

        if(languageID != "OzCyXIQ5LT"){
            ParseQuery<DbSubstanceName> query2 = DbSubstanceName.getQuery();
            query2.whereEqualTo("languageID", "OzCyXIQ5LT");
            List<DbSubstanceName> objects2 = null;
            try {
                objects2 = query2.find();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (objects2 != null && objects2.size() > 0) {
                for (DbSubstanceName item : objects2) {
                    list.add(item.getName());
                }
            }
        }


        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (listener != null) {
            listener.onGetResults(list);
        }
    }
}

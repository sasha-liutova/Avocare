package com.liutova.avocare.network;

import android.content.Context;
import android.os.AsyncTask;

import com.liutova.avocare.R;
import com.liutova.avocare.helper.CompositionTableRow;
import com.liutova.avocare.listener.CompositionFragmentListener;
import com.liutova.avocare.model.DbSafetyLevel;
import com.liutova.avocare.model.DbSafetyLevelDescription;
import com.liutova.avocare.model.DbSubstance;
import com.liutova.avocare.model.DbSubstanceDescription;
import com.liutova.avocare.model.DbSubstanceName;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksandra Liutova on 17-Apr-16.
 */
public class AsyncTaskComposition extends AsyncTask {

    ArrayList<String> inputList;
    String languageID;
    ArrayList<CompositionTableRow> table;
    CompositionFragmentListener listener;
    WeakReference<Context> mCtx;

    public AsyncTaskComposition(ArrayList<String> inputList, String languageID, CompositionFragmentListener listener, Context context) {
        this.inputList = inputList;
        this.languageID = languageID;
        this.listener = listener;
        this.mCtx = new WeakReference<Context>(context);
    }

    @Override
    protected Object doInBackground(Object[] params) {

        table = new ArrayList<CompositionTableRow>();
        int index = 0;
        for (String name : inputList) {

            // for each substance find id (if present)
            ParseQuery<DbSubstanceName> query = DbSubstanceName.getQuery();
            query.whereEqualTo("name", name);
            query.whereEqualTo("languageID", languageID);
            List<DbSubstanceName> objects = null;
            try {
                objects = query.find();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (objects != null && objects.size() > 0) {
                table.add(new CompositionTableRow(index + 1, objects.get(0).getSubstanceID(), objects.get(0).getName()));
            } else if (objects != null && objects.size() == 0) {
                table.add(new CompositionTableRow(index + 1, null, name));
            }
            index++;
        }

        for (CompositionTableRow item : table) {

            if (item.getId() != null) {
                // for each FOUND substance find description
                ParseQuery<DbSubstanceDescription> query2 = DbSubstanceDescription.getQuery();
                query2.whereEqualTo("substanceID", item.getId());
                query2.whereEqualTo("languageID", languageID);
                List<DbSubstanceDescription> objects2 = null;
                try {
                    objects2 = query2.find();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (objects2 != null && objects2.size() > 0) {
                    item.setDescription(objects2.get(0).getDescription());
                }

                ParseQuery<DbSubstance> query7 = DbSubstance.getQuery();
                query7.whereEqualTo("objectId", item.getId());
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
                query8.whereEqualTo("objectId", item.getSafetyLevelID());
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
                query9.whereEqualTo("safetyLevelID", item.getSafetyLevelID());
                query9.whereEqualTo("languageID", languageID);
                List<DbSafetyLevelDescription> objects9 = null;
                try {
                    objects9 = query9.find();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (objects9 != null && objects9.size() > 0) {
                    item.setSafetyLevelDescription(objects9.get(0).getDescription());
                }
            } else {
                item.setDescription(mCtx.get().getString(R.string.substance_not_found) +
                        mCtx.get().getString(R.string.you_can_request));
                item.setSafetyLevel(-10);
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (listener != null) {
            listener.onGetResults(table);
        }
    }
}

package com.liutova.avocare.network;

/**
 * Created by Oleksandra Liutova on 20-Mar-16.
 */
//public class AsyncTaskLanguageID extends AsyncTask {
//
//    String TAG = this.getClass().getName();
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        SharedPreferences sharedPreferences = getSharedPreferences("preferences", MODE_PRIVATE);
//    }
//
//    @Override
//    protected Object doInBackground(Object[] params) {
//
//        String languageCode = getSharedPreferences("preferences", MODE_PRIVATE).getString("languageCode", "");
//        String newLanguageId = "";
//
//        ParseQuery<DbLanguage> query = DbLanguage.getQuery();
//        query.whereEqualTo("code", languageCode);
//        query.findInBackground(new FindCallback<DbLanguage>() {
//            @Override
//            public void done(List<DbLanguage> objects, ParseException e) {
//                if (e == null) {
//                    SharedPreferences.Editor editor = getSharedPreferences("preferences", MODE_PRIVATE).edit();
//                    editor.putString("LanguageId", newLanguageId);
//                    editor.commit();
//                } else {
//                    Log.d(TAG, "Error occured while looking for language id.");
//                }
//            }
//        });
//
//        return null;
//    }
//}

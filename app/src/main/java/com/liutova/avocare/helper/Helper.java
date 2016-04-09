package com.liutova.avocare.helper;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.liutova.avocare.view.activity.BaseActivity;

/**
 * Created by Oleksandra Liutova on 09-Apr-16.
 */
public class Helper {
    public static void hideKeyboard(BaseActivity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyboard(BaseActivity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, 0);
        }
    }
}

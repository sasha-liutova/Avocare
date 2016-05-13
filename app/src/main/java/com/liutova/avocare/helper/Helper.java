package com.liutova.avocare.helper;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.liutova.avocare.R;
import com.liutova.avocare.view.activity.BaseActivity;

import java.util.Locale;

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

    public static Configuration getConfigurationFromPreferences(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        return config;
    }

    public static int getSafetyLevelLayoutID(int level){
        switch(level){
            case -1:
                return R.drawable.level_icon_minus_1;

            case -2:
                return R.drawable.level_icon_minus_2;

            case -3:
                return R.drawable.level_icon_minus_3;

            case 0:
                return R.drawable.level_icon_0;

            case +1:
                return R.drawable.level_icon_plus_1;

            case +2:
                return R.drawable.level_icon_plus_2;

            case +3:
                return R.drawable.level_icon_plus_3;
            case -10:
                return R.drawable.level_icon_unknown;
            default:
                return -10;
        }
    }
}

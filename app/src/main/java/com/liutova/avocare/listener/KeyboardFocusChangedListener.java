package com.liutova.avocare.listener;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Oleksandra Liutova on 07-May-16.
 */
public class KeyboardFocusChangedListener implements View.OnFocusChangeListener {

    Context ctxt;

    public KeyboardFocusChangedListener(Context ctxt) {
        this.ctxt = ctxt;
    }

    public void onFocusChange(View v, boolean hasFocus){

        if(!hasFocus) {
            InputMethodManager imm =  (InputMethodManager) ctxt.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

        }
    }
}

package com.liutova.avocare.listener;

import com.liutova.avocare.helper.CompositionTableRow;

import java.util.ArrayList;

/**
 * Created by Oleksandra Liutova on 17-Apr-16.
 */
public interface CompositionFragmentListener {
    void onGetResults(ArrayList<CompositionTableRow> table);
}

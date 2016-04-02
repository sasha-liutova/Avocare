package com.liutova.avocare.listener;

import com.liutova.avocare.helper.CompositionTableRow;

import java.util.ArrayList;

/**
 * Created by Oleksandra Liutova on 28-Mar-16.
 */
public interface ProductFragmentListener {
    void onGetResults(String productName, int safetyLevel, String safetyLevelDescription, String photoUrl, String productID, ArrayList<CompositionTableRow> table);
}

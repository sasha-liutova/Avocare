package com.liutova.avocare.listener;

/**
 * Created by Oleksandra Liutova on 28-Mar-16.
 */
public interface ProductFragmentListener {
    void onGetResults(String productName, int safetyLevel, String safetyLevelDescription, String photoUrl);
}

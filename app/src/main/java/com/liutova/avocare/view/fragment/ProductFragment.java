package com.liutova.avocare.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liutova.avocare.R;
import com.liutova.avocare.listener.ProductFragmentListener;
import com.liutova.avocare.network.AsyncTaskProductFragment;
import com.liutova.avocare.view.activity.BarcodeScannerActivity;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Oleksandra Liutova on 19-Mar-16.
 */
public class ProductFragment extends BaseFragment implements ProductFragmentListener {

    @Bind(R.id.productName)
    TextView productNameTextView;
    @Bind(R.id.safetyLevel)
    TextView safetyLevelTextView;
    @Bind(R.id.productImage)
    ImageView productImageView;
    @Bind(R.id.favouriteStar)
    ImageView favouriteStarImageView;

    String TAG = this.getClass().getName();

    String barcode;
    boolean isFavourite;

    public static ProductFragment newInstance(String barcodeValue) {

        Bundle args = new Bundle();
        args.putString(BarcodeScannerActivity.TAG_BARCODE, barcodeValue);
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        barcode = getArguments().getString(BarcodeScannerActivity.TAG_BARCODE, "");
        final String languageID = getBaseActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE).getString("LanguageId", "");

        AsyncTaskProductFragment task = new AsyncTaskProductFragment(languageID, barcode, this);
        task.execute();
        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_product;
    }

    @Override
    public void onGetResults(String productName, int safetyLevel, String safetyLevelDescription, String photoUrl, boolean isFavouriteIn) {
        productNameTextView.setText(productName);

        Log.d(TAG, "onGetResults: url: " + photoUrl);
        Picasso.with(getBaseActivity()).load(photoUrl).into(productImageView);

        isFavourite = isFavouriteIn;
        if (isFavouriteIn) {
            favouriteStarImageView.setImageResource(R.drawable.star_full);
        } else {
            favouriteStarImageView.setImageResource(R.drawable.star_empty);
        }

        if (safetyLevelDescription != null) {
            String finalSafetyDescription = getBaseActivity().getString(R.string.general_safety_level_label) + ": " + safetyLevel + "(" + safetyLevelDescription + ")";
            safetyLevelTextView.setText(finalSafetyDescription);
        }
    }

    @OnClick(R.id.favouriteStar)
    public void onFavouriteClick(View view) {
        if (isFavourite) {
            favouriteStarImageView.setImageResource(R.drawable.star_empty);
        } else {
            favouriteStarImageView.setImageResource(R.drawable.star_full);
        }
        isFavourite = !isFavourite;
    }
}

package com.liutova.avocare.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liutova.avocare.AvocareApplication;
import com.liutova.avocare.R;
import com.liutova.avocare.helper.CompositionTableRow;
import com.liutova.avocare.listener.ProductFragmentListener;
import com.liutova.avocare.model.MbFavourites;
import com.liutova.avocare.model.MbHistory;
import com.liutova.avocare.network.AsyncTaskProductFragment;
import com.liutova.avocare.view.activity.BarcodeScannerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

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
    @Bind(R.id.whole_layout)
    View wholeLayoutView;
    @Bind(R.id.not_found_layout)
    View notFoundView;
    @Bind(R.id.blank_layout)
    View blankLayoutView;
    @Bind(R.id.composition_table)
    LinearLayout compositionTableView;

    String TAG = this.getClass().getName();

    String barcode;
    boolean isFavourite;
    String productID;
    Realm realm;

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


        RealmConfiguration realmConfig = new RealmConfiguration.Builder(AvocareApplication.getAppContext()).build();
        realm = Realm.getInstance(realmConfig);

        AsyncTaskProductFragment task = new AsyncTaskProductFragment(languageID, barcode, this, getContext().getApplicationContext());
        task.execute();

        return view;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_product;
    }

    @Override
    public void onGetResults(String productName, int safetyLevel, String safetyLevelDescription, String photoUrl, String productID, ArrayList<CompositionTableRow> table) {

        this.productID = productID;

        // add product to History
        realm.beginTransaction();
        MbHistory record = realm.createObject(MbHistory.class);
        record.setProductName(productName);
        record.setDate(new Date());
        record.setProductID(productID);
        realm.commitTransaction();
        Log.d(TAG, "ProductFragment: added to history: " + productName);

        if (productID != null) {

            notFoundView.setVisibility(View.GONE);
            blankLayoutView.setVisibility(View.GONE);

            productNameTextView.setText(productName);

            Picasso.with(getBaseActivity()).load(photoUrl).into(productImageView);

            RealmResults<MbFavourites> favourites = realm.where(MbFavourites.class).equalTo("productID", productID).findAll();
            if (favourites.size() > 0) {
                isFavourite = true;
            } else {
                isFavourite = false;
            }
            if (isFavourite) {
                favouriteStarImageView.setImageResource(R.drawable.star_full);
            } else {
                favouriteStarImageView.setImageResource(R.drawable.star_empty);
            }

            if (safetyLevelDescription != null) {
                String finalSafetyDescription = getBaseActivity().getString(R.string.general_safety_level_label) + ": " + safetyLevel + "(" + safetyLevelDescription + ")";
                safetyLevelTextView.setText(finalSafetyDescription);
            }

            // fill composition_table layout



            for (CompositionTableRow item: table)
            {
                LinearLayout row = new LinearLayout(getBaseActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);
                row.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                // TODO delete later
                if(item.getName() == null){
                    item.setName("NULL");
                }

                TextView subIndex = new TextView(getBaseActivity());
                subIndex.setLayoutParams(lp2);
                subIndex.setText(item.getIndex() + "");
                row.addView(subIndex);

                TextView subName = new TextView(getBaseActivity());
                subName.setLayoutParams(lp2);
                subName.setText(item.getName());
                row.addView(subName);

                TextView subLevel = new TextView(getBaseActivity());
                subLevel.setLayoutParams(lp2);
                subLevel.setText(item.getSafetyLevel() + "");
                row.addView(subLevel);

                compositionTableView.addView(row);
            }

        } else {
            wholeLayoutView.setVisibility(View.GONE);
            blankLayoutView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.favouriteStar)
    public void onFavouriteClick(View view) {

        realm.beginTransaction();
        if (isFavourite) {
            Log.d(TAG, "deleting favourite");
            favouriteStarImageView.setImageResource(R.drawable.star_empty);
            // delete object from DB
            RealmResults<MbFavourites> results = realm.where(MbFavourites.class).equalTo("productID", productID).findAll();
            results.clear();
        } else {
            Log.d(TAG, "adding favourite");
            favouriteStarImageView.setImageResource(R.drawable.star_full);
            // add new object to DB
            MbFavourites favourite = realm.createObject(MbFavourites.class);
            favourite.setProductID(productID);
        }
        realm.commitTransaction();
        isFavourite = !isFavourite;
    }

    @Override
    public void onDestroyView() {
        realm.close();
        super.onDestroyView();
    }
}

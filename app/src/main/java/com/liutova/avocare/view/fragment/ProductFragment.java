package com.liutova.avocare.view.fragment;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.liutova.avocare.AvocareApplication;
import com.liutova.avocare.R;
import com.liutova.avocare.helper.CompositionTableRow;
import com.liutova.avocare.listener.ProductFragmentListener;
import com.liutova.avocare.listener.ReportErrorListener;
import com.liutova.avocare.model.DbError;
import com.liutova.avocare.model.MbAlergens;
import com.liutova.avocare.model.MbFavourites;
import com.liutova.avocare.model.MbHistory;
import com.liutova.avocare.network.AsyncTaskProductFragment;
import com.liutova.avocare.view.activity.BarcodeScannerActivity;
import com.liutova.avocare.view.adapter.CompositionTableAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Oleksandra Liutova on 19-Mar-16.
 */
public class ProductFragment extends BaseFragment implements ProductFragmentListener, ReportErrorListener {

    static final int historyCapacity = 50;
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
    @Bind(R.id.composition_table)
    RecyclerView compositionTableView;
    String TAG = this.getClass().getName();
    String barcode;
    boolean isFavourite;
    String productID;
    Realm realm;
    String productName;
    String languageID;
    @Bind(R.id.loading_spinner)
    ProgressBar spinner;
    @Bind(R.id.type_composition_btn)
    LinearLayout typeCompositionBtn;
    AsyncTaskProductFragment task;

    public static ProductFragment newInstance(String barcodeValue, String productID) {

        Bundle args = new Bundle();
        args.putString(BarcodeScannerActivity.TAG_BARCODE, barcodeValue);
        args.putString("productID", productID);

        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        spinner.setVisibility(View.VISIBLE);

        barcode = getArguments().getString(BarcodeScannerActivity.TAG_BARCODE);
        productID = getArguments().getString("productID");
        languageID = getBaseActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE).getString("LanguageId", "");


        RealmConfiguration realmConfig = new RealmConfiguration.Builder(AvocareApplication.getAppContext()).build();
        realm = Realm.getInstance(realmConfig);

        task = new AsyncTaskProductFragment(languageID, barcode, productID, this, getContext().getApplicationContext());
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
        this.productName = productName;
        spinner.setVisibility(View.GONE);

        if (productID != null) {

            // add product to History
            RealmResults<MbHistory> results = realm.where(MbHistory.class).findAll();
            results.sort("date", Sort.DESCENDING);

            realm.beginTransaction();
            if (results.size() == historyCapacity) {
                results.last().deleteFromRealm();
            }
            MbHistory record = realm.createObject(MbHistory.class);
            record.setProductName(productName);
            record.setDate(new Date());
            record.setProductID(productID);
            realm.commitTransaction();

            notFoundView.setVisibility(View.GONE);

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

            RealmConfiguration realmConfig = new RealmConfiguration.Builder(AvocareApplication.getAppContext()).build();
            realm = Realm.getInstance(realmConfig);
            RealmResults<MbAlergens> resultsAl = realm.where(MbAlergens.class).findAll();

            ArrayList<String> alergensNamesList = new ArrayList<String>();
            for (MbAlergens item : resultsAl) {
                alergensNamesList.add(item.getName());
            }

            ArrayList<Integer> alergensIndexesList = new ArrayList<>();
            for (CompositionTableRow row : table) {
                if (alergensNamesList.contains(row.getName())) {
                    alergensIndexesList.add(table.indexOf(row));
                }
            }

            compositionTableView.setLayoutManager(new LinearLayoutManager(getContext()));
            CompositionTableAdapter adapter = new CompositionTableAdapter(table, getBaseActivity(), alergensIndexesList);
            compositionTableView.setAdapter(adapter);
            compositionTableView.setItemAnimator(new DefaultItemAnimator());


        } else { // product not found
            wholeLayoutView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.favouriteStar)
    public void onFavouriteClick(View view) {

        realm.beginTransaction();
        if (isFavourite) {
            favouriteStarImageView.setImageResource(R.drawable.star_empty);
            // delete object from DB
            RealmResults<MbFavourites> results = realm.where(MbFavourites.class).equalTo("productID", productID).findAll();
            results.deleteAllFromRealm();
            Toast.makeText(getBaseActivity(), R.string.removed_from_favourites, Toast.LENGTH_SHORT).show();
        } else {
            favouriteStarImageView.setImageResource(R.drawable.star_full);
            // add new object to DB
            MbFavourites favourite = realm.createObject(MbFavourites.class);
            favourite.setProductID(productID);
            favourite.setName(productName);
            Toast.makeText(getBaseActivity(), R.string.added_to_favourites, Toast.LENGTH_SHORT).show();
        }
        realm.commitTransaction();
        isFavourite = !isFavourite;
    }

    @OnClick(R.id.report_error_btn)
    public void onReportErrorBtnClick() {
        FragmentManager fm = getBaseActivity().getFragmentManager();

        ReportErrorDialogFragment dialog = new ReportErrorDialogFragment();
        dialog.setListener(this);
        dialog.show(fm, getBaseActivity().getString(R.string.report_error));
    }

    @Override
    public void onDestroyView() {
        realm.close();
        super.onDestroyView();
    }

    @Override
    public void onSaveErrorReport(String type, String description) {
        DbError report = new DbError();
        report.setType(type);
        report.setStatus("new");
        report.setDescription(description);
        report.setProductID(productID);
        report.setLanguageID(languageID);
        report.saveInBackground();
        Toast.makeText(getContext(), R.string.report_sent, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.type_composition_btn)
    public void onTypeCompositionBtnClick(){
        getBaseActivity().replaceFragment(TypeCompositionFragment.newInstance());
    }

    @Override
    public void onPause() {
        super.onPause();
        task.setListenerToNull();
    }
}

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
import android.widget.Toast;

import com.liutova.avocare.AvocareApplication;
import com.liutova.avocare.R;
import com.liutova.avocare.helper.CompositionTableRow;
import com.liutova.avocare.listener.CompositionFragmentListener;
import com.liutova.avocare.listener.ReportErrorListener;
import com.liutova.avocare.model.DbError;
import com.liutova.avocare.model.MbAlergens;
import com.liutova.avocare.network.AsyncTaskComposition;
import com.liutova.avocare.view.adapter.CompositionTableAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Oleksandra Liutova on 17-Apr-16.
 */
public class CompositionFragment extends BaseFragment implements CompositionFragmentListener, ReportErrorListener {

    String TAG = this.getClass().getName();
    ArrayList<String> enteredSubstanceNames;
    String languageID;
    Realm realm;

    @Bind(R.id.composition_table)
    RecyclerView compositionTableView;
    @Bind(R.id.blank_layout)
    View blankLayoutView;

    public static CompositionFragment newInstance(ArrayList<String> enteredSubstanceNames) {

        Bundle args = new Bundle();
        args.putStringArrayList("enteredSubstanceNames", enteredSubstanceNames);

        CompositionFragment fragment = new CompositionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_composition;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        getBaseActivity().changeTitle(getBaseActivity().getString(R.string.composition));

        enteredSubstanceNames = getArguments().getStringArrayList("enteredSubstanceNames");
        languageID = getBaseActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE).getString("LanguageId", "");

        AsyncTaskComposition task = new AsyncTaskComposition(enteredSubstanceNames, languageID, this, getContext());
        task.execute();
        return v;
    }

    @Override
    public void onGetResults(ArrayList<CompositionTableRow> table) {

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(AvocareApplication.getAppContext()).build();
        realm = Realm.getInstance(realmConfig);
        RealmResults<MbAlergens> results = realm.where(MbAlergens.class).findAll();

        ArrayList<String> alergensNamesList = new ArrayList<String>();
        for (MbAlergens item : results) {
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

        blankLayoutView.setVisibility(View.GONE);
    }

    @OnClick(R.id.report_error_btn)
    public void onReportErrorBtnClick() {
        FragmentManager fm = getBaseActivity().getFragmentManager();

        ReportErrorDialogFragment dialog = new ReportErrorDialogFragment();
        dialog.setListener(this);
        dialog.show(fm, "Report error");
    }

    @Override
    public void onSaveErrorReport(String type, String description) {
        DbError report = new DbError();
        report.setType(type);
        report.setStatus("new");
        report.setDescription(description);
        report.setLanguageID(languageID);
        report.saveInBackground();
        Toast.makeText(getContext(), R.string.report_sent, Toast.LENGTH_LONG).show();
    }
}

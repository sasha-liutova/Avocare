package com.liutova.avocare.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.liutova.avocare.AvocareApplication;
import com.liutova.avocare.R;
import com.liutova.avocare.helper.HistoryTableRow;
import com.liutova.avocare.model.MbHistory;
import com.liutova.avocare.view.adapter.HistoryAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Oleksandra Liutova on 08-Apr-16.
 */
public class HistoryFragment extends BaseFragment {

    @Bind(R.id.history_table)
    RecyclerView historyView;
    @Bind(R.id.blank_layout)
    LinearLayout blankLayoutView;
    @Bind(R.id.main_layout)
    LinearLayout mainLayoutView;
    @Bind(R.id.no_history_layout)
    LinearLayout noHistoryLayoutView;

    Realm realm;
    String languageCode;

    public static HistoryFragment newInstance() {

        Bundle args = new Bundle();

        HistoryFragment fragment = new HistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_history;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = super.onCreateView(inflater, container, savedInstanceState);
        getBaseActivity().changeTitle(getBaseActivity().getString(R.string.history));
        languageCode = getBaseActivity().getSharedPreferences("preferences", getBaseActivity().MODE_PRIVATE).getString("languageCode", null);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(AvocareApplication.getAppContext()).build();
        realm = Realm.getInstance(realmConfig);
        RealmResults<MbHistory> results = realm.where(MbHistory.class).findAllSorted("date", Sort.DESCENDING);

        ArrayList<HistoryTableRow> adapterList = new ArrayList<HistoryTableRow>();
        for (MbHistory item : results) {
            adapterList.add(new HistoryTableRow(formatTimeStamp(item.getDate()), item.getProductName(), item.getProductID()));
        }

        // display history
        if (results.size() > 0) {
            blankLayoutView.setVisibility(View.GONE);
            noHistoryLayoutView.setVisibility(View.GONE);
        } else {
            blankLayoutView.setVisibility(View.GONE);
            mainLayoutView.setVisibility(View.GONE);
        }

        historyView.setLayoutManager(new LinearLayoutManager(getContext()));
        HistoryAdapter adapter = new HistoryAdapter(adapterList, getBaseActivity());
        historyView.setAdapter(adapter);
        historyView.setItemAnimator(new DefaultItemAnimator());

        return v;
    }

    private String formatTimeStamp(Date input) {

        SimpleDateFormat format;
        Date now = new Date();
        // today
        if (DateUtils.isToday(input.getTime())) {
            format = new SimpleDateFormat("HH:mm");
        } else {
            if (languageCode == "en") {
                format = new SimpleDateFormat("MM/dd/yy");
            } else {
                format = new SimpleDateFormat("dd/MM/yy");
            }
        }

        return format.format(input);
    }

    @Override
    public void onDestroyView() {
        realm.close();
        super.onDestroyView();
    }

}

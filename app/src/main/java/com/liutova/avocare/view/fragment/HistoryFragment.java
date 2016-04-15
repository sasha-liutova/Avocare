package com.liutova.avocare.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liutova.avocare.AvocareApplication;
import com.liutova.avocare.R;
import com.liutova.avocare.model.MbHistory;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Oleksandra Liutova on 08-Apr-16.
 */
public class HistoryFragment extends BaseFragment {

    @Bind(R.id.history_table)
    LinearLayout historyTableView;
    @Bind(R.id.blank_layout)
    LinearLayout blankLayoutView;
    @Bind(R.id.main_layout)
    LinearLayout mainLayoutView;
    @Bind(R.id.no_history_layout)
    LinearLayout noHistoryLayoutView;

    Realm realm;

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

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(AvocareApplication.getAppContext()).build();
        realm = Realm.getInstance(realmConfig);
        RealmResults<MbHistory> results = realm.where(MbHistory.class).findAll();

        // display history
        if (results.size() > 0) {
            for (MbHistory item : results) {
                LinearLayout row = new LinearLayout(getBaseActivity());
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);
                row.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                TextView prodName = new TextView(getBaseActivity());
                prodName.setLayoutParams(lp2);
                prodName.setText(item.getProductName());
                row.addView(prodName);

                TextView timeStamp = new TextView(getBaseActivity());
                timeStamp.setLayoutParams(lp2);
                timeStamp.setText(formatTimeStamp(item.getDate() + ""));
                row.addView(timeStamp);

                historyTableView.addView(row);
            }
            blankLayoutView.setVisibility(View.GONE);
            noHistoryLayoutView.setVisibility(View.GONE);
        } else {
            blankLayoutView.setVisibility(View.GONE);
            mainLayoutView.setVisibility(View.GONE);
        }

        return v;
    }

    private String formatTimeStamp(String input) {
        // TODO implement
        return input;
    }

    @Override
    public void onDestroyView() {
        realm.close();
        super.onDestroyView();
    }
}

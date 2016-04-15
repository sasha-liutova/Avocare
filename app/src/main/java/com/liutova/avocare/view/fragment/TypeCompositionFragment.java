package com.liutova.avocare.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.liutova.avocare.R;
import com.liutova.avocare.listener.TypeCompositionFragmentListener;
import com.liutova.avocare.network.AsyncTaskTypeComposition;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Oleksandra Liutova on 14-Apr-16.
 */
public class TypeCompositionFragment extends BaseFragment implements TypeCompositionFragmentListener {

    @Bind(R.id.composition_listview)
    ListView compositionListView;
    private ArrayList<String> substanceNamesList;
    private String TAG = this.getClass().getName();
    private String languageID;

    public static TypeCompositionFragment newInstance() {

        Bundle args = new Bundle();

        TypeCompositionFragment fragment = new TypeCompositionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_type_composition;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        languageID = getBaseActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE).getString("LanguageId", "");
        AsyncTaskTypeComposition task = new AsyncTaskTypeComposition(languageID, this);
        task.execute();

        // fill ListView with dummy data - FOR TESTING
//        ArrayList<String> testList = new ArrayList<>(Arrays.asList("Water", "Something", "Beer", "Rose water"));
//        for (String name : testList) {
//            LinearLayout row = new LinearLayout(getBaseActivity());
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            row.setLayoutParams(lp);
//            row.setOrientation(LinearLayout.HORIZONTAL);
//
//            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//            TextView subName = new TextView(getBaseActivity());
//            subName.setLayoutParams(lp2);
//            subName.setText(name);
//            row.addView(subName);
//
//            TextView timeStamp = new TextView(getBaseActivity());
//            timeStamp.setLayoutParams(lp2);
//            timeStamp.setText("cross");
//            row.addView(timeStamp);
//
//            compositionListView.addView(row);
//        }

        return v;
    }

    @OnClick(R.id.add_button)
    public void onClickAddButton() {

    }

    @Override
    public void onGetResults(ArrayList<String> list) {
        substanceNamesList = list;
    }
}

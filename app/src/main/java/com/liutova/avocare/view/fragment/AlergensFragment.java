package com.liutova.avocare.view.fragment;

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
import com.liutova.avocare.helper.Helper;
import com.liutova.avocare.listener.TypeCompositionFragmentListener;
import com.liutova.avocare.model.MbAlergens;
import com.liutova.avocare.network.AsyncTaskTypeComposition;
import com.liutova.avocare.view.adapter.AlergensAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by liutoole on 4/18/16.
 */
public class AlergensFragment extends BaseFragment implements TypeCompositionFragmentListener {

    AlergensAdapter adapter;
    ArrayList<String> substanceNamesList;
    Realm realm;
    String languageID;
    int serverCallsCounter;

    @Bind(R.id.alergens_listview)
    RecyclerView alergensView;

    public static AlergensFragment newInstance() {

        Bundle args = new Bundle();

        AlergensFragment fragment = new AlergensFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_alergens;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        getBaseActivity().changeTitle(getBaseActivity().getString(R.string.my_allergens));
        serverCallsCounter=0;

        substanceNamesList = new ArrayList<String>();
        languageID = getBaseActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE).getString("LanguageId", "");
        AsyncTaskTypeComposition task = new AsyncTaskTypeComposition(languageID, this);
        task.execute();

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(AvocareApplication.getAppContext()).build();
        realm = Realm.getInstance(realmConfig);
        RealmResults<MbAlergens> results = realm.where(MbAlergens.class).findAll();

        ArrayList<String> adapterList = new ArrayList<String>();
        for (MbAlergens item : results) {
            adapterList.add(item.getName());
        }
        if(adapterList.size() == 0){
            Helper.showKeyboard(getBaseActivity());
        }

        alergensView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AlergensAdapter(getBaseActivity(), adapterList, substanceNamesList);
        alergensView.setAdapter(adapter);
        alergensView.setItemAnimator(new DefaultItemAnimator());

        return v;
    }

    @OnClick(R.id.add_alergen_btn)
    public void onAddAlergen(){
        adapter.add("");
        Helper.showKeyboard(getBaseActivity());
    }

    @OnClick(R.id.alergens_save_btn)
    public void onClickSave() {
        Helper.hideKeyboard(getBaseActivity());
        ArrayList<String> alergensNames = adapter.getItemsData();

        realm.beginTransaction();
        RealmResults<MbAlergens> results = realm.where(MbAlergens.class).findAll();
        results.deleteAllFromRealm();
        for (String name : alergensNames) {
            if (!name.equals("")) {
                MbAlergens item = new MbAlergens(name);
                realm.copyToRealm(item);
            }
        }
        realm.commitTransaction();

        Toast.makeText(getBaseActivity(), R.string.saved, Toast.LENGTH_SHORT).show();
        getBaseActivity().replaceFragment(AlergensFragment.newInstance());
    }

    @Override
    public void onGetResults(ArrayList<String> list) {
        if(list == null && serverCallsCounter<4){
            serverCallsCounter++;
            AsyncTaskTypeComposition task = new AsyncTaskTypeComposition(languageID, this);
            task.execute();
        } else if (list != null) {
            for (String name : list) {
                substanceNamesList.add(name);
            }
        }
    }

    @Override
    public void onDestroyView() {
        realm.close();
        super.onDestroyView();
    }
}

package com.liutova.avocare.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liutova.avocare.AvocareApplication;
import com.liutova.avocare.R;
import com.liutova.avocare.model.MbFavourites;
import com.liutova.avocare.view.adapter.FavouritesAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Oleksandra Liutova on 17-Apr-16.
 */
public class FavouritesFragment extends BaseFragment {

    Realm realm;

    @Bind(R.id.favourites_view)
    RecyclerView favouritesRecyclerView;

    public static FavouritesFragment newInstance() {

        Bundle args = new Bundle();

        FavouritesFragment fragment = new FavouritesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_favourites;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder(AvocareApplication.getAppContext()).build();
        realm = Realm.getInstance(realmConfig);
        RealmResults<MbFavourites> results = realm.where(MbFavourites.class).findAllSorted("name");

        ArrayList<String> adapterList = new ArrayList<String>();
        for (MbFavourites item : results) {
            adapterList.add(item.getName());
        }

        favouritesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FavouritesAdapter adapter = new FavouritesAdapter(adapterList);
        favouritesRecyclerView.setAdapter(adapter);
        favouritesRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return v;
    }
}

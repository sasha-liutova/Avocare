package com.liutova.avocare.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.liutova.avocare.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Oleksandra Liutova on 17-Apr-16.
 */
public class SettingsFragment extends BaseFragment {

    public static ArrayList<Pair<String, String>> languagesPairs;
    List<String> options;
    @Bind(R.id.settings_lang_spinner)
    Spinner spinner;

    public static SettingsFragment newInstance() {

        Bundle args = new Bundle();

        languagesPairs = new ArrayList<Pair<String, String>>();
        languagesPairs.add(new Pair<String, String>("en", "English"));
        languagesPairs.add(new Pair<String, String>("cs", "Čeština"));

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_settings;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        options = new ArrayList<String>();
        for (Pair<String, String> pair : languagesPairs) {
            options.add(pair.second);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return v;
    }
}

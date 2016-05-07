package com.liutova.avocare.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.liutova.avocare.R;
import com.liutova.avocare.helper.Helper;
import com.liutova.avocare.network.AsyncTaskLanguageID;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Oleksandra Liutova on 17-Apr-16.
 */
public class SettingsFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {

    public static ArrayList<Pair<String, String>> languagesPairs;
    static boolean isFirstSelection;
    String TAG = this.getClass().getName();
    List<String> options;
    @Bind(R.id.settings_lang_spinner)
    Spinner spinner;

    public static SettingsFragment newInstance() {

        Bundle args = new Bundle();

        languagesPairs = new ArrayList<Pair<String, String>>();
        languagesPairs.add(new Pair("en", "English"));
        languagesPairs.add(new Pair("cs", "Čeština"));

        isFirstSelection = true;

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
        getBaseActivity().changeTitle(getBaseActivity().getString(R.string.settings));

        options = new ArrayList<String>();
        for (Pair<String, String> pair : languagesPairs) {
            options.add(pair.second);
        }

        String languageCode = getBaseActivity().getSharedPreferences("preferences", getBaseActivity().MODE_PRIVATE).getString("languageCode", null);

        String languageName = "";
        for (Pair<String, String> pair : languagesPairs) {
            if (pair.first == languageCode) {
                languageName = pair.second;
                break;
            }
        }
        int index = options.indexOf(languageName);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(index);
        spinner.setOnItemSelectedListener(this);

        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (isFirstSelection) {
            isFirstSelection = false;
        } else {
            String item = parent.getItemAtPosition(position).toString();
            for (Pair<String, String> pair : languagesPairs) {
                if (pair.second == item) {
                    String newLanguageCode = pair.first;
                    SharedPreferences.Editor editor = getBaseActivity().getSharedPreferences("preferences", getBaseActivity().MODE_PRIVATE).edit();
                    editor.putString("languageCode", newLanguageCode);
                    editor.commit();
                    Log.d(TAG, "Languages: new language code: " + newLanguageCode);
                    AsyncTaskLanguageID task = new AsyncTaskLanguageID(getBaseActivity().getApplicationContext());
                    task.execute();
                    getBaseActivity().getResources().updateConfiguration(Helper.getConfigurationFromPreferences(newLanguageCode), getBaseActivity().getResources().getDisplayMetrics());
                    break;
                }
            }
            getBaseActivity().replaceFragment(SettingsFragment.newInstance());
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

package com.liutova.avocare.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.liutova.avocare.R;
import com.liutova.avocare.model.DbProductDescription;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnTextChanged;
import io.realm.Realm;

/**
 * Created by Oleksandra Liutova on 09-Apr-16.
 */
public class EnterProductNameFragment extends BaseFragment {

    @Bind(R.id.found_products_listview)
    ListView productsListView;
    String TAG = this.getClass().getName();

    Realm realm;
    String languageID;

    public static EnterProductNameFragment newInstance() {

        Bundle args = new Bundle();

        EnterProductNameFragment fragment = new EnterProductNameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_enter_product_name;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        languageID = getBaseActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE).getString("LanguageId", "");

        // pull products names from Parse
        ParseQuery<DbProductDescription> query = DbProductDescription.getQuery();
        query.whereEqualTo("languageID", languageID);
        List<DbProductDescription> objects = null;
        try {
            objects = query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // extract products names to separate array
        ArrayList<String> productsNamesList = new ArrayList<>();
        for (DbProductDescription item : objects) {
            productsNamesList.add(item.getName());
        }
        Object[] objectList = productsNamesList.toArray();
        String[] productsNamesArray = Arrays.copyOf(objectList, objectList.length, String[].class);
        //String[]productsNamesArray = (String[]) productsNamesList.toArray();

        ArrayAdapter adapter = new ArrayAdapter<String>(getBaseActivity(), R.layout.item_product_name, R.id.item_product_name_textview, productsNamesArray);
        productsListView.setAdapter(adapter);

        return v;
    }

    @OnTextChanged(R.id.enter_product_name_edittext)
    public void onEnterProductNameTextChanged(CharSequence input) {
        Log.d(TAG, "onEnterProductNameTextChanged: Text has been changed to " + input.toString());
    }
}

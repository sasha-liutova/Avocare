package com.liutova.avocare.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.liutova.avocare.R;
import com.liutova.avocare.helper.Helper;
import com.liutova.avocare.listener.KeyboardFocusChangedListener;
import com.liutova.avocare.model.DbProductDescription;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;
import io.realm.Realm;

/**
 * Created by Oleksandra Liutova on 09-Apr-16.
 */
public class EnterProductNameFragment extends BaseFragment {

    @Bind(R.id.found_products_listview)
    ListView productsListView;
    @Bind(R.id.enter_product_name_edittext)
    EditText enterProductEditTxt;

    String TAG = this.getClass().getName();

    Realm realm;
    String languageID;
    ArrayList<String> productsNamesList;
    ArrayList<String> productsIDsList;
    ArrayList<String> currentProductsIDsList;
    ArrayAdapter adapter;

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
        KeyboardFocusChangedListener listener = new KeyboardFocusChangedListener(getBaseActivity());
        enterProductEditTxt.setOnFocusChangeListener(listener);
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
        productsNamesList = new ArrayList<>();
        productsIDsList = new ArrayList<>();
        currentProductsIDsList = new ArrayList<>();

        for (DbProductDescription item : objects) {
            productsNamesList.add(item.getName());
            productsIDsList.add(item.getProductID());
            //currentProductsIDsList.add(item.getProductID());
        }

        adapter = new ArrayAdapter<String>(getBaseActivity(), R.layout.item_product_name, R.id.item_product_name_textview, new ArrayList<String>());
        productsListView.setAdapter(adapter);

        Helper.showKeyboard(getBaseActivity());

        enterProductEditTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Helper.hideKeyboard(getBaseActivity());
                    getBaseActivity().replaceFragment(ProductFragment.newInstance(null, null));
                    return true;
                }
                return false;
            }
        });

        return v;
    }

    @OnTextChanged(R.id.enter_product_name_edittext)
    public void onEnterProductNameTextChanged(CharSequence input) {

        currentProductsIDsList.clear();
        ArrayList<String> currentProductsNamesList = new ArrayList<>();

        if (input.length() > 0) {
            for (String name : productsNamesList) {
                if (name.toLowerCase().contains(input.toString().toLowerCase())) {
                    currentProductsNamesList.add(name);
                    currentProductsIDsList.add(productsIDsList.get(productsNamesList.indexOf(name)));
                }
            }
        }

        adapter.clear();
        adapter.addAll(currentProductsNamesList);
        adapter.notifyDataSetChanged();
    }

    @OnItemClick(R.id.found_products_listview)
    public void onClickFoundProductsListview(int position) {

        Helper.hideKeyboard(getBaseActivity());
        getBaseActivity().replaceFragment(ProductFragment.newInstance(null, currentProductsIDsList.get(position)));
    }
}

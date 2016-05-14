package com.liutova.avocare.view.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.liutova.avocare.R;
import com.liutova.avocare.view.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Oleksandra Liutova on 14-May-16.
 */
public class ProductSafetyDialogFragment extends DialogFragment {

    private static final String KEY_NAME = "key_name";
    private static final String KEY_DESC = "key_desc";
    private static final String KEY_S_LEVEL = "key_safety_level";
    private static final String KEY_S_LEVEL_DESC = "key_safety_level_description";
    String name;
    String description;
    String safetyLevel;
    String safetyLevelDescription;

    @Bind(R.id.product_name)
    TextView productNameView;
    @Bind(R.id.safety_level_description)
    TextView safetyDescriptionView;

    public ProductSafetyDialogFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public static ProductSafetyDialogFragment newInstance(String name, String description, String safetyLevel, String safetyLevelDescription, BaseActivity activity) {

        Bundle args = new Bundle();
        args.putString(KEY_NAME, name);
        if (description != null && !description.equals("")) {
            args.putString(KEY_DESC, description);
        } else {
            args.putString(KEY_DESC, activity.getString(R.string.no_description));
        }
        args.putString(KEY_S_LEVEL, safetyLevel);
        args.putString(KEY_S_LEVEL_DESC, safetyLevelDescription);
        ProductSafetyDialogFragment fragment = new ProductSafetyDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.dialog_fragment_product_safety, container);
        ButterKnife.bind(this, v);
        name = getArguments().getString(KEY_NAME);
        description = getArguments().getString(KEY_DESC);
        safetyLevel = getArguments().getString(KEY_S_LEVEL);
        safetyLevelDescription = getArguments().getString(KEY_S_LEVEL_DESC);
        productNameView.setText(name);

        if(safetyLevel.equals("-10")){
            safetyDescriptionView.setText(getString(R.string.safety_level_is) + " " + getString(R.string.unknown));
        } else{
            safetyDescriptionView.setText(getString(R.string.safety_level_is) + " " + safetyLevel + " (" + safetyLevelDescription + ").");
        }

        return v;
    }

}

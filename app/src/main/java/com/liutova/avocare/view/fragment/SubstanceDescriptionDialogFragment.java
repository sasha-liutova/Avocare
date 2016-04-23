package com.liutova.avocare.view.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liutova.avocare.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Oleksandra Liutova on 23-Apr-16.
 */
public class SubstanceDescriptionDialogFragment extends DialogFragment {

    private static final String KEY_NAME = "key_name";
    private static final String KEY_DESC = "key_desc";
    private static final String KEY_S_LEVEL = "key_safety_level";
    private static final String KEY_S_LEVEL_DESC = "key_safety_level_description";
    String name;
    String description;
    String safetyLevel;
    String safetyLevelDescription;

    @Bind(R.id.substance_description_safety)
    TextView substanceDescSafetyView;
    @Bind(R.id.substance_description_text)
    TextView substanceDescTextView;

    public SubstanceDescriptionDialogFragment() {
    }

    public static SubstanceDescriptionDialogFragment newInstance(String name, String description, String safetyLevel, String safetyLevelDescription) {

        Bundle args = new Bundle();
        args.putString(KEY_NAME, name);
        args.putString(KEY_DESC, description);
        args.putString(KEY_S_LEVEL, safetyLevel);
        args.putString(KEY_S_LEVEL_DESC, safetyLevelDescription);
        SubstanceDescriptionDialogFragment fragment = new SubstanceDescriptionDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.dialog_fragment_substance_description, container);
        ButterKnife.bind(this, v);
        name = getArguments().getString(KEY_NAME);
        description = getArguments().getString(KEY_DESC);
        safetyLevel = getArguments().getString(KEY_S_LEVEL);
        safetyLevelDescription = getArguments().getString(KEY_S_LEVEL_DESC);
        getDialog().setTitle(name);
        substanceDescSafetyView.setText("Safety level is " + safetyLevel + " (" + safetyLevelDescription + ").");
        substanceDescTextView.setText(description);
        return v;
    }
}

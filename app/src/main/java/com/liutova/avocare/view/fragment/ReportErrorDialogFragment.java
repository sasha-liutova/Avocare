package com.liutova.avocare.view.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.liutova.avocare.R;

/**
 * Created by Oleksandra Liutova on 21-Apr-16.
 */
public class ReportErrorDialogFragment extends DialogFragment {

    EditText editText;
    Spinner spinner;

    public ReportErrorDialogFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_report_error, container);
        editText = (EditText) view.findViewById(R.id.error_description);
        spinner = (Spinner) view.findViewById(R.id.error_type_spinner);
        getDialog().setTitle(R.string.report_error);

        return view;
    }
}

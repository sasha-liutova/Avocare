package com.liutova.avocare.view.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.liutova.avocare.R;
import com.liutova.avocare.listener.ReportErrorListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * Created by Oleksandra Liutova on 21-Apr-16.
 */
public class ReportErrorDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {

    EditText editText;
    Spinner spinner;
    List<String> options;
    String description;
    String type;
    ReportErrorListener listener;
    Button btnCancel;
    Button btnSave;

    public ReportErrorDialogFragment() {
    }

    public void setListener(ReportErrorListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_report_error, container);
        ButterKnife.bind(this, view);
        editText = (EditText) view.findViewById(R.id.error_description);
        spinner = (Spinner) view.findViewById(R.id.error_type_spinner);
        btnCancel = (Button) view.findViewById(R.id.report_error_cancel_btn);
        btnSave = (Button) view.findViewById(R.id.report_error_send_btn);

        spinner.setOnItemSelectedListener(this);

        options = new ArrayList<>();
        options.add("Content");
        options.add("Syntax");
        options.add("Technical");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, options);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        getDialog().setTitle(R.string.report_error);

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = options.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnTextChanged(R.id.error_description)
    public void onTextChengedErrorDescription(CharSequence input) {
        description = input.toString();
    }

    @OnClick(R.id.report_error_send_btn)
    public void onSendErrorReport() {
        listener.onSaveErrorReport(type, description);
        this.dismiss();
    }

    @OnClick(R.id.report_error_cancel_btn)
    public void onCancelErrorReport() {
        this.dismiss();
    }

}

package com.liutova.avocare.view.fragment;

import android.os.Bundle;

import com.liutova.avocare.R;

/**
 * Created by Oleksandra Liutova on 13-Mar-16.
 */
public class BarcodeReaderFragment extends BaseFragment {

    public static BarcodeReaderFragment newInstance() {

        Bundle args = new Bundle();

        BarcodeReaderFragment fragment = new BarcodeReaderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_barcode_reader;
    }

    /*@OnClick(R.id.process_button)
    public void onClickProcessBtn(View view) {
        @Bind(R.id.imgview) ImageView myImageView;
        //ImageView myImageView = (ImageView) findViewById(R.id.imgview);
        Bitmap myBitmap = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.puppy);
        myImageView.setImageBitmap(myBitmap);
    }*/
}

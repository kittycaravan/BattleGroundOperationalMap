package com.peisia.bgom;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;

public class ActivityHome extends AppCompatActivity {

    private Spinner mSpinnerMapSelector;
    private ImageView mImageViewFullMap;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;

        initView();
    }

    private void initView() {
        mImageViewFullMap = (ImageView)findViewById(R.id.imageViewFullMap);

        mSpinnerMapSelector = (Spinner)findViewById(R.id.spinnerMapSelector);
        mSpinnerMapSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Glide.with(mContext).load(R.drawable.map_full_erangel).into(mImageViewFullMap);
                        break;
                    case 1:
                        Glide.with(mContext).load(R.drawable.map_full_miramar).into(mImageViewFullMap);
                        break;
                    default:
                        Glide.with(mContext).load(R.drawable.map_full_erangel).into(mImageViewFullMap);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSpinnerMapSelector.setSelection(0);
    }
}

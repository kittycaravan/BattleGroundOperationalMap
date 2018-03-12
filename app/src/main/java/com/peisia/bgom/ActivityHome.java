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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ActivityHome extends AppCompatActivity {
    private AdView mAdView;
    private Spinner mSpinnerMapSelector;
    private ImageView mImageViewFullMap;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;
        initAdMob();    // 광고 초기화 및 세팅 모두
        initView();
    }

    private void initAdMob() {
        MobileAds.initialize(this, "ca-app-pub-1478183271915956~6143017977");   // 내 광고 id 적어주기
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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

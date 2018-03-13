package com.peisia.bgom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.peisia.bgom.view.ImageViewFullMap;

public class ActivityHome extends AppCompatActivity {
    private AdView mAdView;
    private Spinner mSpinnerMapSelector;
    private ImageView mImageViewFullMap;
    private ImageViewFullMap mImageViewFullMapForDraw;
    private Context mContext;

    private int mTouchCount = 0;
    private float mTouchPointFirstX = 0;
    private float mTouchPointFirstY = 0;
    private float mTouchPointSecondX = 0;
    private float mTouchPointSecondY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContext = this;
        initAdMob();    // 광고 초기화 및 세팅 모두
        initView();
        initTouch();
    }

    private void initTouch() {

        mImageViewFullMap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();
                Log.v("hoyangi","==== x : "+x);
                Log.v("hoyangi","==== y : "+y);
                mTouchCount++;
                if(mTouchCount == 1){
                    mTouchPointFirstX = x;
                    mTouchPointFirstY = y;
                    mImageViewFullMapForDraw.drawFirstCircle(mTouchPointFirstX, mTouchPointFirstY);
                } else if(mTouchCount == 2){
                    //todo: 그리기 수행(선을 그리기)
                    mTouchPointSecondX = x;
                    mTouchPointSecondY = y;
                    mImageViewFullMapForDraw.drawSecondCircle(mTouchPointSecondX, mTouchPointSecondY);
                } else {    // 3이상이면
                    //todo: 지우고 다시 값 세팅
                }
                return false;
            }
        });
    }

    private void initAdMob() {
        MobileAds.initialize(this, "ca-app-pub-1478183271915956~6143017977");   // 내 광고 id 적어주기
        mAdView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void initView() {
        mImageViewFullMap = (ImageView)findViewById(R.id.imageViewFullMap);
        mImageViewFullMapForDraw = (ImageViewFullMap) findViewById(R.id.imageViewFullMapForDraw);

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

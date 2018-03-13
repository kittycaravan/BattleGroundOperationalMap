package com.peisia.bgom.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 호양이 on 2018-03-13.
 */

public class ImageViewFullMap extends View {
    private static final float FIRST_DOT_RADIUS = 10;   // 첫 터치 지점에 그려지는 점의 반지름 크기
    private static final float AIRPLANE_PATH_LINE_WIDTH = 4;                // 비행기 경로 라인 두께
    private static final float AIRPLANE_PATH_LINE_AREA_WIDTH_FIRST = 100;     // 비행기 경로 영역  - 1지역
    private static final float AIRPLANE_PATH_LINE_AREA_WIDTH_SECOND = 200;     // 비행기 경로 영역 - 2지역

    private static final int AIRPLANE_PATH_LINE_AREA_COLOR_FIRST  = 0x55FF0000;   // 비행기 경로 영역 - 1지역
    private static final int AIRPLANE_PATH_LINE_AREA_COLOR_SECOND = 0x55FFBF24;   // 비행기 경로 영역 - 2지역



    private Canvas mCanvasFullMap;

    private float firstX;
    private float firstY;
    private float secondX;
    private float secondY;

    private int mode;

    public ImageViewFullMap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvasFullMap = canvas;

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);

        switch(mode){
            case 1 :
                paint.setColor(Color.RED);
                mCanvasFullMap.drawCircle(firstX, firstY, FIRST_DOT_RADIUS, paint);
                break;
            case 2 :
                ////    비행기의 경로 주변 경계 지역 칠하기 - 2지역    ////
                paint.setColor(AIRPLANE_PATH_LINE_AREA_COLOR_SECOND);
                paint.setStrokeWidth(AIRPLANE_PATH_LINE_AREA_WIDTH_SECOND);
                mCanvasFullMap.drawLine(firstX, firstY, secondX, secondY, paint);   // 경계 지역 그리기

                ////    비행기의 경로 주변 경계 지역 칠하기 - 1지역    ////
                paint.setColor(AIRPLANE_PATH_LINE_AREA_COLOR_FIRST);
                paint.setStrokeWidth(AIRPLANE_PATH_LINE_AREA_WIDTH_FIRST);
                mCanvasFullMap.drawLine(firstX, firstY, secondX, secondY, paint);   // 경계 지역 그리기

                ////    비행기의 경로 그리기   ////
                paint.setColor(Color.GREEN);
                paint.setStrokeWidth(AIRPLANE_PATH_LINE_WIDTH);
                mCanvasFullMap.drawLine(firstX, firstY, secondX, secondY, paint);   // 선 그리기

                ////    첫 포인트 점으로 그리기   ////
                paint.setColor(Color.RED);
                mCanvasFullMap.drawCircle(firstX, firstY, FIRST_DOT_RADIUS, paint);

                ////    두번째 포인트 점으로 그리기   ////
                paint.setColor(Color.BLUE);
                mCanvasFullMap.drawCircle(secondX, secondY, FIRST_DOT_RADIUS, paint);

                break;
        }
    }

    public void drawFirstCircle(float x, float y){
        firstX = x;
        firstY = y;
        mode = 1;
        invalidate();
    }

    public void drawSecondCircle(float x, float y){
        secondX = x;
        secondY = y;
        mode = 2;
        invalidate();
    }
}

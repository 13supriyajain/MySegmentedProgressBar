package com.supjain.mysegmentedprogressbar;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView mImageView = (ImageView) findViewById(R.id.image_view);

        Bitmap bitmap = Bitmap.createBitmap(
            getScreenWidth(), // Width
                40, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);

        Paint arrowPaint = new Paint();
        arrowPaint.setStyle(Paint.Style.FILL);
        arrowPaint.setColor(Color.BLACK);
        arrowPaint.setStrokeWidth(5);

        Paint textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);

        int segmentCount = 7;
        int filledSegmentCount = 4;
        int gap = 40;

        int marginFromTop = 5;
        int progressBarHeight = 30;

        int marginFromLeft = gap/2;
        int progressBarSegmentWidth = (int)(getScreenWidth() / segmentCount) - gap;
        int marginFromRight = marginFromLeft + progressBarSegmentWidth;

        for(int i = 0; i < filledSegmentCount; i++)
        {
            drawRectangle(canvas, marginFromLeft, marginFromTop, marginFromRight, progressBarHeight, paint);
            //canvas.drawText(String.valueOf(i+1), (marginFromLeft + marginFromRight - gap - gap - gap)/2, progressBarHeight - gap, textPaint);
            marginFromLeft = marginFromLeft + progressBarSegmentWidth + gap;
            if(i < filledSegmentCount && i < segmentCount-1) {
                canvas.drawLine(marginFromRight, progressBarHeight / 2, marginFromLeft, progressBarHeight / 2, arrowPaint);
                canvas.drawText(">", marginFromLeft - 20, progressBarHeight, textPaint);
            }
            marginFromRight = marginFromLeft + progressBarSegmentWidth;
        }

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        //textPaint.setColor(Color.BLACK);
        for(int i = filledSegmentCount; i < segmentCount; i++)
        {
            drawRectangle(canvas, marginFromLeft, marginFromTop, marginFromRight, progressBarHeight, paint);
            //canvas.drawText(String.valueOf(i+1), (marginFromLeft + marginFromRight - gap - gap - gap)/2, progressBarHeight - gap, textPaint);
            marginFromLeft = marginFromLeft + progressBarSegmentWidth + gap;
            if(i < segmentCount-1) {
                canvas.drawLine(marginFromRight, progressBarHeight / 2, marginFromLeft, progressBarHeight / 2, arrowPaint);
                canvas.drawText(">", marginFromLeft - 20, progressBarHeight, textPaint);
            }
            marginFromRight = marginFromLeft + progressBarSegmentWidth;
        }

        mImageView.setImageBitmap(bitmap);
    }


    public void drawRectangle(Canvas canvas, int marginFromLeft, int marginFromTop,
                              int marginFromRight, int progressBarHeight, Paint paint) {
        Rect rectangle = new Rect(
                marginFromLeft, // Left
                marginFromTop, // Top
                marginFromRight, // Right
                progressBarHeight // Bottom
        );

        canvas.drawRect(rectangle,paint);
    }

    public int getScreenWidth() {
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        return displayMetrics.widthPixels;
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}

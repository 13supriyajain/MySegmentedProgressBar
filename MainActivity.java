package com.supjain.mysegmentedprogressbar;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView mImageView = (ImageView) findViewById(R.id.image_view);

        Bitmap bitmap = Bitmap.createBitmap(
            getScreenWidth(), // Width
                100, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.light_green));

        Paint textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(50);

        int segmentCount = 7;
        int filledSegmentCount = 4;

        int marginFromTop = 40;
        int progressBarHeight = 60;

        int marginFromLeft = 0;
        int progressBarSegmentWidth = (int)(getScreenWidth() / segmentCount);
        int marginFromRight = marginFromLeft + progressBarSegmentWidth;
        int circleRadius = 40;

        for(int i = 0; i < filledSegmentCount; i++)
        {
            drawRectangle(canvas, marginFromLeft, marginFromTop, marginFromRight, progressBarHeight, paint);
            canvas.drawCircle((marginFromRight+marginFromLeft)/2, progressBarHeight-10, circleRadius, paint);
            canvas.drawText(String.valueOf(i+1),((marginFromRight+marginFromLeft)/2)-15, progressBarHeight+10, textPaint);
            marginFromLeft = marginFromLeft + progressBarSegmentWidth;
            marginFromRight = marginFromLeft + progressBarSegmentWidth;
        }

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.light_orange));
        textPaint.setColor(Color.BLACK);
        for(int i = filledSegmentCount; i <= segmentCount; i++)
        {
            drawRectangle(canvas, marginFromLeft, marginFromTop, marginFromRight, progressBarHeight, paint);
            if(i < segmentCount) {
                canvas.drawCircle((marginFromRight+marginFromLeft)/2, progressBarHeight-10, circleRadius, paint);
                canvas.drawText(String.valueOf(i+1),((marginFromRight+marginFromLeft)/2)-15, progressBarHeight+10, textPaint);
                marginFromLeft = marginFromLeft + progressBarSegmentWidth;
                marginFromRight = marginFromLeft + progressBarSegmentWidth;
            }
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
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}

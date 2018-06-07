package com.supjain.mysegmentedprogressbar;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.graphics.drawable.VectorDrawableCompat;
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
                150, // Height
                Bitmap.Config.ARGB_8888 // Config
        );

        Canvas canvas = new Canvas(bitmap);

        VectorDrawableCompat vectorDrawable = VectorDrawableCompat.create(this.getResources(), R.drawable.ic_check_circle_black_24dp, null);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.light_green));

        int segmentCount = 7;
        int filledSegmentCount = 3;
        int circleRadius = 40;

        int marginFromTop = 50;
        int progressBarHeight = 60;

        int vectorTop = 0;
        int vectorBottom = 110;

        int marginFromLeft = 0;
        int marginFromRight = 0;
        int progressBarSegmentWidth = (int)(getScreenWidth() / (segmentCount-1)) -15 - (2 * circleRadius) ;

        for(int i = 0; i < filledSegmentCount; i++)
        {
            //canvas.drawCircle(marginFromRight, progressBarHeight-10, circleRadius, paint);
            marginFromRight = marginFromRight + 2 * circleRadius;
            vectorDrawable.setBounds(marginFromLeft, vectorTop, marginFromRight,vectorBottom);
            vectorDrawable.draw(canvas);
            marginFromLeft = marginFromRight - 5;
            marginFromRight = marginFromLeft + progressBarSegmentWidth;
            drawRectangle(canvas, marginFromLeft, marginFromTop, marginFromRight, progressBarHeight, paint);
            marginFromLeft = marginFromRight - 5;
        }

        Paint circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);
        circlePaint.setColor(getResources().getColor(R.color.light_green));

        marginFromRight = marginFromRight + circleRadius;
        canvas.drawCircle(marginFromRight, progressBarHeight-10, circleRadius, circlePaint);
        marginFromLeft = marginFromRight + circleRadius;
        marginFromRight = marginFromLeft + progressBarSegmentWidth;

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        for(int i = filledSegmentCount; i < segmentCount-1; i++)
        {
            drawRectangle(canvas, marginFromLeft, marginFromTop, marginFromRight, progressBarHeight-7, paint);
            marginFromRight = marginFromRight + circleRadius;
            canvas.drawCircle(marginFromRight, progressBarHeight-10, circleRadius, paint);
            marginFromLeft = marginFromRight + circleRadius;
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
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}

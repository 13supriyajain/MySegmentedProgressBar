package com.supjain.mysegmentedprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

        Context context = getApplicationContext();

        int circleRadius = convertDpToPixel(20, context);
        int circleDiameter = 2 * circleRadius;
        int vectorDiameter = circleDiameter + 10;

        int marginFromTop = convertDpToPixel(25, context);
        int progressBarHeight = convertDpToPixel(30, context);
        int emptyBarHeight = progressBarHeight - 8;

        int vectorTop = 0;
        int vectorBottom = convertDpToPixel(55, context);

        int marginFromLeft = 0;
        int marginFromRight = 0;
        int offset = 5;

        int progressBarSegmentWidth = (int)((getScreenWidth() - 30 - (segmentCount * circleDiameter)) / (segmentCount-1));

        for(int i = 0; i < filledSegmentCount; i++)
        {
            marginFromRight = marginFromRight + vectorDiameter;
            vectorDrawable.setBounds(marginFromLeft, vectorTop, marginFromRight,vectorBottom);
            vectorDrawable.draw(canvas);
            marginFromLeft = marginFromRight - offset;
            marginFromRight = marginFromLeft + progressBarSegmentWidth;
            drawRectangle(canvas, marginFromLeft, marginFromTop, marginFromRight, progressBarHeight, paint);
            marginFromLeft = marginFromRight - offset;
        }

        Paint circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(5);
        circlePaint.setColor(getResources().getColor(R.color.light_green));

        marginFromRight = marginFromRight + circleRadius;
        canvas.drawCircle(marginFromRight, progressBarHeight, circleRadius, circlePaint);
        marginFromLeft = marginFromRight + circleRadius;
        marginFromRight = marginFromLeft + progressBarSegmentWidth;

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GRAY);
        for(int i = filledSegmentCount; i < segmentCount-1; i++)
        {
            drawRectangle(canvas, marginFromLeft, marginFromTop, marginFromRight, emptyBarHeight, paint);
            marginFromRight = marginFromRight + circleRadius;
            canvas.drawCircle(marginFromRight, progressBarHeight, circleRadius, paint);
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

    public int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int) dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
}

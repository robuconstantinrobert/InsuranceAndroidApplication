package com.example.incercarelicenta6.Circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class CircularProgressBar extends View {
    private static final int START_ANGLE = -90; // Start angle for progress bar
    private static final int MAX_ANGLE = 360; // Maximum angle for progress bar

    private Paint backgroundPaint;
    private Paint progressPaint;
    private RectF rectF;

    private int progress;
    private int maxProgress;

    public CircularProgressBar(Context context) {
        super(context);
        init();
    }

    public CircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        backgroundPaint = new Paint();
        backgroundPaint.setColor(Color.LTGRAY);
        backgroundPaint.setStyle(Paint.Style.STROKE);
        backgroundPaint.setStrokeWidth(20);

        progressPaint = new Paint();
        progressPaint.setColor(Color.BLUE);
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setStrokeWidth(20);

        rectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int diameter = Math.min(getWidth(), getHeight()) - 40;
        rectF.set(centerX - diameter / 2, centerY - diameter / 2, centerX + diameter / 2, centerY + diameter / 2);

        canvas.drawOval(rectF, backgroundPaint);

        float sweepAngle = MAX_ANGLE * ((float) progress / maxProgress);
        canvas.drawArc(rectF, START_ANGLE, sweepAngle, false, progressPaint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }
}

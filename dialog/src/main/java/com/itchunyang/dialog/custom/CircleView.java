package com.itchunyang.dialog.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by luchunyang on 2017/1/3.
 */

public class CircleView extends View {

    private float width;
    private Paint paint;
    private float startAngle;
    private ValueAnimator animator;

    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(8);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth() > getHeight() ? getHeight():getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.argb(100, 255, 255, 255));
        canvas.drawCircle(width /2,width/2,width / 2 - 5,paint);
        paint.setColor(Color.WHITE);
        RectF rectF = new RectF(5,5,width - 5,width - 5);
        canvas.drawArc(rectF,startAngle,100,false,paint);
    }

    public void startAnim(){

        stopAnim();

        animator = ValueAnimator.ofFloat(0f,1f);
        animator.setDuration(700);
        animator.setRepeatCount(ValueAnimator.INFINITE);//无限
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value= (float) animation.getAnimatedValue();
                startAngle = 360 * value;
                postInvalidate();
            }
        });

        if(!animator.isRunning())
            animator.start();
    }

    public void stopAnim(){
        if(animator != null){
            clearAnimation();
            animator.setRepeatCount(1);
            animator.cancel();
            animator.end();
        }
    }
}

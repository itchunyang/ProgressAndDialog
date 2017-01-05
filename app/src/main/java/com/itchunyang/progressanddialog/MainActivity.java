package com.itchunyang.progressanddialog;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

/**
 * 常见属性
 * style 属性
 * ?android:attr/progressBarStyleSmall   灰色圆形 逆时针 小号
 * ?android:attr/progressBarStyle        灰色圆形 逆时针 中号
 * ?android:attr/progressBarStyleLarge   灰色圆形 逆时针 大号
 * ?android:attr/progressBarStyleHorizontal 水平进度条
 *
 * android:indeterminate    不确定模式，在不确定模式下，进度条动画无限循环. 该属性一般在进度条为横向情况（Widget.ProgressBar.Horizontal）下才设置，因为圆形进度条本身就是模糊模式。
 * android:indeterminateBehavior 当进度达到最大时,不确定模式的表现.repeat表示进度从0重新开始；cycle表示进度保持当前值，并且回到0
 * android:indeterminateDuration 设置不明确的进度条的一个周期的时间
 * android:interpolator 差值器
 *
 * android:progressDrawable 这个属性是 确定的进度条的属性
 * android:indeterminateDrawable 这个事不确定的进度条的属性
 *              如果是帧Drawable，则无需设置android:indeterminateDuration,
 *              如果是其他Drawable，需要设置android:indeterminateDuration
 */
public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private ProgressBar progressBar1;
    private ProgressBar progressBar2;

    private int progress ;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

    }

    public void start(View view) {
        progress = 0;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(progress <= 100)
                    handler.postDelayed(this,25);

                progressBar.setProgress(++progress);
                progressBar1.setProgress(++progress);
                progressBar2.setProgress(++progress);
            }
        },25);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}

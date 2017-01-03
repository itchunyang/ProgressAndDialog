package com.itchunyang.dialog.custom;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itchunyang.dialog.R;

/**
 * Created by luchunyang on 2017/1/3.
 */

public class LoadingDialog{
    public static final String TAG = LoadingDialog.class.getSimpleName();
    private Dialog mDialog;
    private CircleView circleView;

    public LoadingDialog(Context context,String text){
        View view = LayoutInflater.from(context).inflate(R.layout.custom_loading_view,null);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.loadingView);
        circleView = (CircleView) view.findViewById(R.id.circleView);
        TextView tv = (TextView) view.findViewById(R.id.loading_text);
        tv.setText(text);
        mDialog = new Dialog(context,R.style.loading_dialog);
//        mDialog.setCancelable(false);
        mDialog.setContentView(linearLayout,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void show(){
        mDialog.show();
        WindowManager.LayoutParams params = mDialog.getWindow().getAttributes();
        params.width = 400;
        params.height = 200;
        mDialog.getWindow().setGravity(Gravity.BOTTOM);
        mDialog.getWindow().setAttributes(params);
        circleView.startAnim();
    }

    public void close(){
        if(mDialog != null){
            circleView.stopAnim();
            mDialog.dismiss();
            mDialog = null;
        }
    }



}

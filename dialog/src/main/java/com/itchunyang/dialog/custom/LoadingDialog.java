package com.itchunyang.dialog.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.itchunyang.dialog.R;

/**
 * Created by luchunyang on 2017/1/3.
 */

public class LoadingDialog extends AlertDialog {

    private TextView tv;

    public LoadingDialog(Context context,String text) {
        this(context,R.style.LoadingDialog);

        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog_two,null);
        tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(text);

        setContentView(view);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected LoadingDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

}

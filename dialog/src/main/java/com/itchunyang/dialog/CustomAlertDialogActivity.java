package com.itchunyang.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.itchunyang.dialog.custom.LoadingDialog;

public class CustomAlertDialogActivity extends AppCompatActivity {

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_alert_dialog);
        inflater = getLayoutInflater();
    }

    public void customDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = inflater.inflate(R.layout.custom_dialog_one,null);
        builder.setView(v);
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
        final Dialog dialog = builder.create();
        dialog.show();

        Button btn = (Button) v.findViewById(R.id.okButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void loading(View view) {
        LoadingDialog dialog = new LoadingDialog(this,"玩命加载中...");
        dialog.show();
    }
}

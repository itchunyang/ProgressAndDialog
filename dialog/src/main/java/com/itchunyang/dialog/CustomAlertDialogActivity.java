package com.itchunyang.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class CustomAlertDialogActivity extends AppCompatActivity {

    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setWindowAnimations(R.style.DialogAnimation);
        setContentView(R.layout.activity_custom_alert_dialog);
        inflater = getLayoutInflater();
    }

    public void test(View view) {
        View v = LayoutInflater.from(this).inflate(R.layout.custom_dialog_two,null);
        TextView tv = (TextView) v.findViewById(R.id.tv);
        tv.setText("正在加载...");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("标题栏");
        builder.setMessage("message");

        //builder.setView(v);//View 和 message可以共存  message和setItems不能共存
        AlertDialog dialog = builder.create();

        dialog.setView(v);//show方法前调用 ==builder.setView(v)
        dialog.show();
        //dialog.setContentView(v); //==dialog.getWindow().setContentView(v); show()后调用

    }

    public void loading(View view) {

        View v = LayoutInflater.from(this).inflate(R.layout.custom_dialog_two,null);
        TextView tv = (TextView) v.findViewById(R.id.tv);
        tv.setText("正在加载...");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Dialog dialog = builder.create();
        dialog.show();

        dialog.setContentView(v);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;

        params.width = width/4;
        params.height = height / 10;
        dialog.getWindow().setAttributes(params);

    }

    public void materialDialog(View view) {
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



    public void dialogGravity(View view) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        View v = LayoutInflater.from(this).inflate(R.layout.custom_content,null);
//        builder.setTitle("Custom Dialog");
//        AlertDialog dialog = builder.create();
//        dialog.setView(v,0,0,0,0);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_content);
        dialog.setTitle("Custom Dialog");

        Window window = dialog.getWindow();
        //当参数值包含Gravity.RIGHT时,对话框出现在右边,所以lp.x就表示相对右边的偏移,负值忽略
        window.setGravity(Gravity.LEFT | Gravity.TOP);
//        window.setGravity(Gravity.BOTTOM);

        dialog.show();

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.65);
//        params.height = (int) (getResources().getDisplayMetrics().widthPixels * 0.6);
        params.x = 100;// 新位置X坐标
        params.y = 100;// 新位置Y坐标

        dialog.getWindow().setAttributes(params);
    }

    public void dialogAnimation(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("自定义Dialog动画");
        builder.setMessage("this is message");
        builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        Dialog dialog = builder.create();
        Window window = dialog.getWindow();
//        window.setWindowAnimations(R.style.DialogAnimation);
        window.setWindowAnimations(R.style.DialogAnimation1);
        dialog.show();
    }
}

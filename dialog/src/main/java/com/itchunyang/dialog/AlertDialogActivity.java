package com.itchunyang.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class AlertDialogActivity extends AppCompatActivity {

    public static final String TAG = "AlertDialog";

    //dialog取消事件 当Dialog调用cancel()时或者点击外部区域取消才会触发,按钮的dismiss方法不会触发此事件
    private DialogInterface.OnCancelListener cancelListener = new DialogInterface.OnCancelListener() {
        @Override
        public void onCancel(DialogInterface dialog) {
            Toast.makeText(AlertDialogActivity.this,"onCancel",Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * dialog dismiss事件
     * OnCancelListener 和 OnDismissListener区别？
     * 看源码知道，如果Dialog设置了setOnCancelListener，那么cancel 比dismiss要多做一些任务，否则dismiss和cancel等同
     */

    private DialogInterface.OnDismissListener dismissListener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            Toast.makeText(AlertDialogActivity.this,"onDismiss",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
    }

    public void defaultDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("冲田杏梨").setIcon(ContextCompat.getDrawable(this,R.drawable.title_icon));
        //可以自定义标题栏，会覆盖系统默认的
        //builder.setCustomTitle(LayoutInflater.from(this).inflate(R.layout.custom_title,null));

        builder.setMessage("日本动作美女明星,您有没有心动?");
        //自定义内容View，但是会和上面系统默认的同时存在，需要把上面的去掉
        //builder.setView(LayoutInflater.from(this).inflate(R.layout.custom_content,null));

        builder.setOnCancelListener(cancelListener);
        //builder.setOnDismissListener(dismissListener);

        builder.setPositiveButton("心动了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick: which="+which);//-1
            }
        });
        builder.setNegativeButton("不喜欢", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick: which="+which);//-2
            }
        });

        //builder.setCancelable(false);

        final AlertDialog dialog = builder.create();
        //为了可以直观看到，Dialog的背景稍微大了一圈的
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.RED));
        dialog.show();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(dialog.isShowing())
                    dialog.cancel();
            }
        },8000);
    }

    private String[] avGirls = {"冲田杏梨","松岛枫","泽井芽衣","田中瞳"};
    private boolean[] isCheckedItems = {false,false,true,true};
    public void singleChoiceDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择一个美女带回家");
        builder.setSingleChoiceItems(avGirls, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this,avGirls[which],Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        }).create().show();
    }

    public void multipleChoiceDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("选择一下那个美女和你Make Love");
        builder.setMultiChoiceItems(avGirls, isCheckedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                isCheckedItems[which] = isChecked;
            }
        });
        builder.setPositiveButton("选好了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "onClick: "+ Arrays.toString(isCheckedItems));
            }
        }).create().show();
    }

    private String[] avGirlsUS = {"BiBi Jones","ashlynn brooke","Kayden Kross","Tori black","Jenna Presley","Sophie Moon",
            "Rosa Caracciolo","Erica Ellyson","Cytherea"};

    public void listDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //可以这么简单的设置
//        builder.setItems(avGirlsUS, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.custom_simple_list_item,R.id.tv,avGirlsUS);
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(AlertDialogActivity.this,avGirlsUS[which],Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        //设置高度限制，如果不设置那么只有在高度达到屏幕大小后，listview才会开始滚动
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.height = 350;
        dialog.getWindow().setAttributes(params);
    }

    public void theme1(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.MyDialogTheme);
        builder.setTitle("这是标题");
        builder.setMessage("这是详细信息");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

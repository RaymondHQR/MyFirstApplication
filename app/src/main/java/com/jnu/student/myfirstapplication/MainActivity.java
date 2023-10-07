package com.jnu.student.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv_top;
    private TextView tv_bottom;

//    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView = findViewById(R.id.text_view_hello_world);
//        textView.setText(R.string.hello_android);

        tv_top = findViewById(R.id.tv_top);
        tv_bottom = findViewById(R.id.tv_bottom);
        findViewById(R.id.btn_change).setOnClickListener(v -> {
            String tmp = (String) tv_top.getText();
            tv_top.setText(tv_bottom.getText());
            tv_bottom.setText(tmp);
            // alert
            Toast.makeText(this, "交换成功！", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setMessage("交换成功！");
            builder.create().show();
        });
    }
}
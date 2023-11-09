package com.jnu.student.myfirstapplication.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.jnu.student.myfirstapplication.R;
import com.jnu.student.myfirstapplication.base.BaseAppCompatActivity;

public class BookDetailActivity extends BaseAppCompatActivity {

    private EditText et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        String name = getIntent().getStringExtra("name");
        int id = getIntent().getIntExtra("id", -1);

        et_name = findViewById(R.id.et_input);
        et_name.setText(name);

        findViewById(R.id.btn_confirm).setOnClickListener(v -> {
            String current = et_name.getText().toString();
            if (current.equals("")) {
                Toast.makeText(this, "请输入书名！", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();
            intent.putExtra("name", current);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        findViewById(R.id.btn_cancel).setOnClickListener(v -> {
            setResult(Activity.RESULT_CANCELED);
            finish();
        });
    }
}
package com.jnu.student.myfirstapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.jnu.student.myfirstapplication.adapter.ShopItemAdapter;
import com.jnu.student.myfirstapplication.bean.Book;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ActivityResultLauncher<Intent> addBookLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        recyclerView = findViewById(R.id.recycle_view_books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("软件项目管理案例教程（第4版）", R.drawable.book_2));
        books.add(new Book("创新工程实践", R.drawable.book_no_name));
        books.add(new Book("信息安全数学基础（第2版）", R.drawable.book_1));

        ShopItemAdapter shopItemAdapter = new ShopItemAdapter(books);
        recyclerView.setAdapter(shopItemAdapter);

        registerForContextMenu(recyclerView);

        addBookLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String name = data.getStringExtra("name");
                        books.add(new Book(name, R.drawable.book_2));
                        shopItemAdapter.notifyItemInserted(books.size());
                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                    }
                }
        );
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {
            case 0:

                break;
            case 1:
                break;
            case 2:
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
}
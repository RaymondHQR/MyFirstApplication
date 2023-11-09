package com.jnu.student.myfirstapplication.views;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import com.jnu.student.myfirstapplication.R;
import com.jnu.student.myfirstapplication.adapter.ShopItemAdapter;
import com.jnu.student.myfirstapplication.base.BaseAppCompatActivity;
import com.jnu.student.myfirstapplication.bean.Book;
import com.jnu.student.myfirstapplication.utils.FileUtils;

import java.util.ArrayList;

public class RecycleActivity extends BaseAppCompatActivity {

    private RecyclerView recyclerView;
    ActivityResultLauncher<Intent> addBookLauncher;
    ActivityResultLauncher<Intent> editBookLauncher;
    private ArrayList<Book> books;
    private ShopItemAdapter shopItemAdapter;
    private FileUtils fileUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        recyclerView = findViewById(R.id.recycle_view_books);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fileUtils = new FileUtils(this);

        books = new ArrayList<>();

        Object storeData = fileUtils.readFile("data");
        if (storeData != null) {
            books = (ArrayList<Book>) storeData;
            Toast.makeText(this, "成功读取存储的信息！", Toast.LENGTH_SHORT).show();
        }
        else {
            books.add(new Book("软件项目管理案例教程（第4版）", R.drawable.book_2));
            books.add(new Book("创新工程实践", R.drawable.book_no_name));
            books.add(new Book("信息安全数学基础（第2版）", R.drawable.book_1));
        }

        shopItemAdapter = new ShopItemAdapter(books);
        recyclerView.setAdapter(shopItemAdapter);

        registerForContextMenu(recyclerView);

        addBookLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String name = data.getStringExtra("name");
                            books.add(new Book(name, R.drawable.book_2));
                            shopItemAdapter.notifyItemInserted(books.size());
                            Toast.makeText(this, "添加书本成功！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        editBookLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String name = data.getStringExtra("name");
                            books.add(new Book(name, R.drawable.book_no_name));
                            shopItemAdapter.notifyItemInserted(books.size());
                            Toast.makeText(this, "修改书本成功！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        findViewById(R.id.btn_save).setOnClickListener(v -> {
            fileUtils.writeFile("data", books);
            Toast.makeText(this, "保存成功！", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        switch (item.getItemId()) {
            case 0:
                addBookLauncher.launch(new Intent(this, AddBookActivity.class));
                break;
            case 1:
                books.remove(shopItemAdapter.getCurrentPosition());
                shopItemAdapter.notifyItemRemoved(shopItemAdapter.getCurrentPosition());
                Toast.makeText(this, "删除成功！", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Intent intent = new Intent(this, BookDetailActivity.class);
                intent.putExtra("name", books.get(shopItemAdapter.getCurrentPosition()).getTitle());
                intent.putExtra("id", shopItemAdapter.getCurrentPosition());
                editBookLauncher.launch(intent);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
}
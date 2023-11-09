package com.jnu.student.myfirstapplication.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.jnu.student.myfirstapplication.R;
import com.jnu.student.myfirstapplication.adapter.ShopItemAdapter;
import com.jnu.student.myfirstapplication.bean.Book;
import com.jnu.student.myfirstapplication.utils.FileUtils;
import com.jnu.student.myfirstapplication.views.AddBookActivity;
import com.jnu.student.myfirstapplication.views.BookDetailActivity;

import java.util.ArrayList;

public class BookFragment extends Fragment {

    ActivityResultLauncher<Intent> addBookLauncher;
    ActivityResultLauncher<Intent> editBookLauncher;
    private ArrayList<Book> books;
    private ShopItemAdapter shopItemAdapter;
    private FileUtils fileUtils;

    public BookFragment() {

    }

    public static BookFragment newInstance() {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_books);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fileUtils = new FileUtils(getActivity());

        books = new ArrayList<>();

        Object storeData = fileUtils.readFile("data");
        if (storeData != null) {
            books = (ArrayList<Book>) storeData;
            Toast.makeText(getActivity(), "成功读取存储的信息！", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getActivity(), "添加书本成功！", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getActivity(), "修改书本成功！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        view.findViewById(R.id.btn_save).setOnClickListener(v -> {
            fileUtils.writeFile("data", books);
            Toast.makeText(getActivity(), "保存成功！", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                addBookLauncher.launch(new Intent(getActivity(), AddBookActivity.class));
                break;
            case 1:
                books.remove(shopItemAdapter.getCurrentPosition());
                shopItemAdapter.notifyItemRemoved(shopItemAdapter.getCurrentPosition());
                Toast.makeText(getActivity(), "删除成功！", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Intent intent = new Intent(getActivity(), BookDetailActivity.class);
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
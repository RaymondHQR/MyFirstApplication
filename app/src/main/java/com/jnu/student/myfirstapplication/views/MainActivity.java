package com.jnu.student.myfirstapplication.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jnu.student.myfirstapplication.R;
import com.jnu.student.myfirstapplication.adapter.MainActivityViewPagerFragmentAdapter;
import com.jnu.student.myfirstapplication.base.BaseAppCompatActivity;

public class MainActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 vp_main = findViewById(R.id.vp_main);
        vp_main.setAdapter(new MainActivityViewPagerFragmentAdapter(getSupportFragmentManager(), getLifecycle()));

        TabLayout tl_header = findViewById(R.id.tl_header);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tl_header, vp_main, (tab, position) -> {
            switch(position) {
                case 0:
                    tab.setText("图书");
                    break;
                case 1:
                    tab.setText("新闻");
                    break;
                case 2:
                    tab.setText("地图");
                    break;
            }
        });

        tabLayoutMediator.attach();
    }
}
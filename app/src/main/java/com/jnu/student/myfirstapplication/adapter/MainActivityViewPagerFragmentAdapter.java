package com.jnu.student.myfirstapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.jnu.student.myfirstapplication.fragments.BookFragment;
import com.jnu.student.myfirstapplication.fragments.TencentMapFragment;
import com.jnu.student.myfirstapplication.fragments.WebViewFragment;

public class MainActivityViewPagerFragmentAdapter extends FragmentStateAdapter {

    public MainActivityViewPagerFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position) {
            case 0:
                return BookFragment.newInstance();
            case 1:
                return WebViewFragment.newInstance("http://news.baidu.com/");
            case 2:
                return TencentMapFragment.newInstance();
        }
        return BookFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}

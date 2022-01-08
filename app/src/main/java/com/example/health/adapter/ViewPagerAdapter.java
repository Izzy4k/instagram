package com.example.health.adapter;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.example.health.Fragment.CameraFragment;
import com.example.health.Fragment.DirectFragment;
import com.example.health.Fragment.InstFragment;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerAdapter extends FragmentPagerAdapter {
 private   List<Fragment> fragmentList = new ArrayList<>();
 private List<String> fragmentTitleList = new ArrayList<>();


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

    }
    public  void addFragment(Fragment fragment , String title){
        fragmentList.add(fragment);
        fragmentTitleList.add(title);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

          return  fragmentList.get(position);
    }


    @Override
    public int getCount() {
        return fragmentList.size();
    }
}

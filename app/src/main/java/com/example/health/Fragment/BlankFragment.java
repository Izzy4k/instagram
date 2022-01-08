package com.example.health.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.health.Interface.BackTop;
import com.example.health.Interface.ViewPagerClick;
import com.example.health.MainActivity;
import com.example.health.R;
import com.example.health.adapter.ViewPagerAdapter;
import com.example.health.databinding.FragmentBlankBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;


public class BlankFragment extends Fragment implements ViewPagerClick {
    static FragmentBlankBinding binding;
    NavController controller;
    public static ViewPagerAdapter adapter;
    private InstFragment instFragment = new InstFragment();
    private CameraFragment cameraFragment = new CameraFragment();
    private DirectFragment directFragment = new DirectFragment();
    public static int pos = 1;


    public BlankFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = Navigation.findNavController(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBlankBinding.inflate(inflater);
        initPager();
        initListener();

        instFragment.addInter(this);
        cameraFragment.addInter(this);
        return binding.getRoot();
    }


    private void initListener() {
        binding.viewPagerBlank.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pos = position;
                switch (position) {
                    case 0:
                        cameraFragment.cameraStart();
                        MainActivity.setVisibility(View.GONE);
                        break;
                    case 1:
                        cameraFragment.releaseCamera();
                        MainActivity.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        MainActivity.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initPager() {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(cameraFragment, "camera");
        adapter.addFragment(instFragment, "inst");
        adapter.addFragment(directFragment, "direct");
        binding.viewPagerBlank.setAdapter(adapter);
        binding.viewPagerBlank.setCurrentItem(1);
        directFragment.addInter(this);
    }

    @Override
    public void back() {
        binding.viewPagerBlank.setCurrentItem(pos - 1);
    }

    @Override
    public void next() {
        binding.viewPagerBlank.setCurrentItem(pos + 1);
    }


    public static void top() {
        binding.viewPagerBlank.setCurrentItem(1);
    }
}
package com.example.health.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.health.Interface.ViewPagerClick;
import com.example.health.R;
import com.example.health.databinding.FragmentDirectBinding;
import com.google.android.material.tabs.TabLayout;

public class DirectFragment extends Fragment  {
    FragmentDirectBinding binding;
    ViewPagerClick click;

    public DirectFragment() {
    }

    public void addInter(ViewPagerClick click) {
        this.click = click;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDirectBinding.inflate(inflater, container, false);
        initTable();
        addFragment(new ChatFragment());
        return binding.getRoot();
    }

    private void initTable() {

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.back();
            }
        });

        binding.tableLayoutDirect.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        addFragment(new ChatFragment());

                        break;
                    case 1:

                        addFragment(new RoomFragment());
                        break;
                    case 2:

                        addFragment(new NotesFragment());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_top, fragment);
        transaction.commit();
    }



}
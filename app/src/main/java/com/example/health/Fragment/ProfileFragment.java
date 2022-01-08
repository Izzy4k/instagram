package com.example.health.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.health.Model.StoriesProfileModel;
import com.example.health.R;
import com.example.health.adapter.StoriesProfileAdapter;
import com.example.health.databinding.FragmentProfileBinding;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    private StoriesProfileAdapter storiesProfileAdapter ;
    private List<StoriesProfileModel> list ;
     NavController controller;
    public ProfileFragment() {

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(storiesProfileAdapter == null){
            storiesProfileAdapter = new StoriesProfileAdapter(requireContext());
        }
        binding.rvStoriesProfile.setAdapter(storiesProfileAdapter);
        initView();
    }

    private void initView() {
        if(list == null){
            list = new ArrayList<>();
            List<Integer> image = new ArrayList<>();
            image.add(R.drawable.noka);
            image.add(R.drawable.noka);
            image.add(R.drawable.noka);
            list.add(new StoriesProfileModel(true));
            list.add(new StoriesProfileModel(image,false,"Friend"));
            list.add(new StoriesProfileModel(image,false,"Friend"));
            list.add(new StoriesProfileModel(image,false,"Friend"));
            storiesProfileAdapter.addList(list);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        return binding.getRoot() ;
    }
}
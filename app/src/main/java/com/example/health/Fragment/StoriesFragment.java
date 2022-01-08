package com.example.health.Fragment;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.health.Model.StoriesModel;
import com.example.health.R;
import com.example.health.databinding.FragmentStoriesBinding;

import java.util.ArrayList;
import java.util.List;


public class StoriesFragment extends Fragment {
    private FragmentStoriesBinding binding;
    private StoriesModel model;
    private int pos = 0;
    private NavController controller;
    private List<StoriesModel> list;


    public StoriesFragment() {


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
        binding = FragmentStoriesBinding.inflate(inflater, container, false);
        initTop();
        initListener();
        initTimer();
        initClick();
        binding.timer.getProgressDrawable().setColorFilter(Color.BLACK , PorterDuff.Mode.SRC_IN);
        return binding.getRoot();
    }

    private void initClick() {
        binding.hrest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.popBackStack(R.id.blankFragment, false);
            }
        });
    }

    private void initTimer() {
        new CountDownTimer(10000,100){

            @Override
            public void onTick(long millisUntilFinished) {
                binding.timer.setProgress((int) (millisUntilFinished  / 100));
            }
            @Override
            public void onFinish() {
            controller.popBackStack(R.id.blankFragment,false);
            }
        }.start();
    }

    private void initListener() {
        binding.layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos != 0) {
                    pos--;
                    if (model.getStories() != null) {
                        Glide.with(binding.storiesMainPhoto).load(model.getStories().get(pos)).centerCrop().into(binding.storiesMainPhoto);
                    } else if (model.getPhotoStories() != null) {
                        Glide.with(binding.storiesMainPhoto).load(model.getPhotoStories().get(pos)).centerCrop().into(binding.storiesMainPhoto);
                    }
                }
            }
        });
        binding.layoutNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getStories() != null && pos == model.getStories().size() - 1) {
                    controller.popBackStack(R.id.blankFragment, false);
                } else if (pos == model.getPhotoStories().size() - 1) {
                    controller.popBackStack(R.id.blankFragment, false);
                } else {
                    pos++;
                    if (model.getStories() != null) {
                        Glide.with(binding.storiesMainPhoto).load(model.getStories().get(pos)).centerCrop().into(binding.storiesMainPhoto);
                    } else if (model.getPhotoStories() != null) {
                        Glide.with(binding.storiesMainPhoto).load(model.getPhotoStories().get(pos)).centerCrop().into(binding.storiesMainPhoto);
                    }
                }

            }
        });
    }

    private void initTop() {
        if (getArguments() != null) {
            model = (StoriesModel) requireArguments().getSerializable("model");
            binding.txtNickStories.setText(model.getNick());
            if (model.getStories() != null) {
                getStoriesImage();
            } else if (model.getPhotoStories() != null) {
                getPhotoImage();
            }

        }
    }

    private void getPhotoImage() {
        Glide.with(binding.storiesProfile).load(model.getPhotoStories().get(pos)).circleCrop().into(binding.storiesProfile);
        Glide.with(binding.storiesMainPhoto).load(model.getPhotoStories().get(pos)).centerCrop().into(binding.storiesMainPhoto);
    }

    private void getStoriesImage() {
        Glide.with(binding.storiesProfile).load(model.getStories().get(pos)).circleCrop().into(binding.storiesProfile);
        Glide.with(binding.storiesMainPhoto).load(model.getStories().get(pos)).centerCrop().into(binding.storiesMainPhoto);

    }
}
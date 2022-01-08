package com.example.health.Buttom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.health.R;
import com.example.health.adapter.ShapeAdapter;
import com.example.health.Model.ShapeModel;
import com.example.health.databinding.ButtomSheetSecondBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class ButtonSecond extends BottomSheetDialogFragment {
    ButtomSheetSecondBinding binding;
    ShapeAdapter shapeAdapter;
    private List<ShapeModel> list = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shapeAdapter = new ShapeAdapter(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ButtomSheetSecondBinding.inflate(inflater, container, false);
        initView();
        binding.rvShape.setAdapter(shapeAdapter);
        return binding.getRoot();
    }

    private void initView() {
    list.add(new ShapeModel("Nick","Drug", R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    list.add(new ShapeModel("Nick","Drug",R.drawable.azi));
    shapeAdapter.addList(list);
    }
}

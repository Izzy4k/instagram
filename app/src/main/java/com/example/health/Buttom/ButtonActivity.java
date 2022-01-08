package com.example.health.Buttom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.health.databinding.ButtomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ButtonActivity extends BottomSheetDialogFragment {
    ButtomSheetBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ButtomSheetBinding.inflate(inflater,container,false);
        binding.btnTextCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return binding.getRoot();
    }
}

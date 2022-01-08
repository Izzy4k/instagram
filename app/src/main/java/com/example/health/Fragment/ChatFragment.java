package com.example.health.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.health.Interface.CameraTop;
import com.example.health.Model.DirectModel;
import com.example.health.R;
import com.example.health.adapter.DirectAdapter;
import com.example.health.databinding.FragmentChatBinding;
import java.util.ArrayList;
import java.util.List;


public class ChatFragment extends Fragment  implements CameraTop {
    FragmentChatBinding binding;
    DirectAdapter directAdapter;
    List<DirectModel> list = new ArrayList<>();

    public ChatFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        directAdapter = new DirectAdapter(requireContext());
        initChat();
        binding.rvChatDirect.setAdapter(directAdapter);
        directAdapter.addInter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater);

        return binding.getRoot();
    }

    private void initChat() {
        list.add(new DirectModel("Nick", "Просмотрено", R.drawable.raatbek, true,false));
        list.add(new DirectModel("Nick", "Просмотрено", R.drawable.raatbek, true,false));
        list.add(new DirectModel("Nick", "Просмотрено", R.drawable.raatbek, false,false));
        list.add(new DirectModel("Nick", "Просмотрено", R.drawable.raatbek, false,false));
        list.add(new DirectModel("Nick", "Просмотрено", R.drawable.raatbek, true,false));
        list.add(new DirectModel("Nick", "Просмотрено", R.drawable.raatbek, true,false));
        list.add(new DirectModel("Nick", "Просмотрено", R.drawable.raatbek, true,false));
        directAdapter.addDirect(list);


    }

    @Override
    public void click() {
        if(getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA},3);
        } else {
            getCamera();
        }
    }

    private void getCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 3){
            getCamera();
        }
    }
}
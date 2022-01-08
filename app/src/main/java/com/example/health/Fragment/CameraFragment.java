package com.example.health.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;

import com.example.health.CameraPreview;
import com.example.health.Interface.CameraClick;
import com.example.health.Interface.ViewPagerClick;
import com.example.health.databinding.FragmentCameraBinding;


public class CameraFragment extends Fragment implements    Camera.PictureCallback, Camera.PreviewCallback, Camera.AutoFocusCallback {
    FragmentCameraBinding binding;
    public Camera camera;
    private CameraPreview cameraPreview;
    private Camera.PictureCallback mCallBack;
    private boolean cameraFront = false;
    private ViewPagerClick click;

    public CameraFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCameraBinding.inflate(inflater);
//        cameraStart();
        return binding.getRoot();
    }

    public void cameraStart() {
        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, 2);
        } else {
            getCamera();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            getCamera();
        }
    }

    private void getCamera() {
        camera = Camera.open();
        camera.setDisplayOrientation(90);
        cameraPreview = new CameraPreview(requireContext(), camera);
        binding.cameraPreview.addView(cameraPreview);
        camera.startPreview();
        initButton();
    }

    private void initButton() {
        binding.btnChangeCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numberCamera = Camera.getNumberOfCameras();
                if (numberCamera > 1) {
                    releaseCamera();
                    chooseCamera();
                }
            }
        });
        binding.btnBackCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.next();
            }
        });
    }

    private int findFrontCamera() {
        int cameraId = -1;
        int numberCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                cameraFront = true;
                break;
            }
        }
        return cameraId;
    }

    private int findBackCamera() {
        int cameraId = -1;
        int numberCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;
            }
        }
        return cameraId;
    }

    public void releaseCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.setPreviewCallback(null);
            camera.release();
            camera = null;
        }
    }



    public void chooseCamera() {
        if (cameraFront) {
            int cameraId = findBackCamera();
            if (cameraId >= 0) {
                camera = Camera.open(cameraId);
                camera.setDisplayOrientation(90);
                cameraPreview.refreshCamera(camera);
            }
        } else {
            int cameraId = findFrontCamera();
            if (cameraId >= 0) {
                camera = Camera.open(cameraId);
                camera.setDisplayOrientation(90);
                cameraPreview.refreshCamera(camera);
            }
        }
    }

    public void addInter(ViewPagerClick click) {
        this.click = click;
    }

    @Override
    public void onAutoFocus(boolean success, Camera camera) {
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {

    }

    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {

    }
}
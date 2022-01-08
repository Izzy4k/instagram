package com.example.health;

import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
public SurfaceHolder surfaceHolder;
    private Camera Mcamera;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        this.Mcamera = camera;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        try {
            if (Mcamera == null) {
                Mcamera.setPreviewDisplay(holder);
                Mcamera.startPreview();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        refreshCamera(Mcamera);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    public void refreshCamera(Camera camera) {
        if (surfaceHolder.getSurface() == null) {
            return;
        }
        try {
            camera.stopPreview();
        } catch (Exception e) {

        }
        setCamera(camera);
        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {

        }
    }

    private void setCamera(Camera camera) {
        Mcamera = camera;
    }

//    public void handleFocus(MotionEvent event, Camera.Parameters parameters) {
//        int pointerId = event.getPointerId(0);
//        int pointerIndex = event.findPointerIndex(pointerId);
//        float x = event.getX(pointerIndex);
//        float y = event.getY(pointerIndex);
//        Rect rect = new Rect(
//                (int) (x - 100),
//                (int) (y - 100),
//                (int) (x + 100),
//                (int) (y + 100));
//        final Rect targetFocusRect = new Rect( rect.left * 2000/surfaceHolder. - 1000,
//                rect.top * 2000/mTextureView.getHeight() - 1000,
//                rect.right * 2000/mTextureView.getWidth() - 1000,
//                rect.bottom * 2000/mTextureView.getHeight() - 1000);
//    }

}

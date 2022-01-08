package com.example.health;

import android.content.Context;
import android.view.View;

import com.example.health.Interface.DoubleClickCall;

public class DoubleListener implements View.OnClickListener{
    private boolean isRunning  = false ;
    private int resentInTime = 500 ;
    private int counter = 0 ;

    private DoubleClickCall clickCall ;

    public DoubleListener(Context context){
        clickCall = (DoubleClickCall) context ;
    }
    @Override
    public void onClick(View v) {
        if(isRunning){
            if(counter == 1)
                clickCall.onDoubleClick(v);

        }
        counter++;
        if(!isRunning){
            isRunning = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(resentInTime);
                        isRunning = false;
                        counter = 0;
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

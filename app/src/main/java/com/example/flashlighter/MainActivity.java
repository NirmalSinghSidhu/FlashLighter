package com.example.flashlighter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    Switch aSwitch;
    Boolean isFlashOn=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch=findViewById(R.id.switch1);
        aSwitch.setOnCheckedChangeListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if(compoundButton==aSwitch){
          if(isChecked){
              turn_on_flash_light();
          }
          else{
              turn_off_flash_light();
          }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void turn_off_flash_light() {
        CameraManager cameraManager=(CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String camera_id=cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(camera_id,false);
            isFlashOn=false;
        } catch (CameraAccessException e) {
            System.out.println("on error"+e);
            e.printStackTrace();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void turn_on_flash_light() {
        CameraManager cameraManager=(CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            String camera_id=cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(camera_id,true);
            isFlashOn=true;
        } catch (CameraAccessException e) {
            System.out.println("on error"+e);
            e.printStackTrace();
        }
    }
}
package com.kingnet.nerve;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.kingnet.nerve.annotation.DataEnumDef;
import com.kingnet.nerve.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    Nerve nerve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nerve = new Nerve.Builder(MainActivity.this).setUploadDataType(Nerve.MEMORY).create();
                nerve.start();

            }
        });

        binding.stopFps.setOnClickListener(v -> {
//            nerve.stop();
            checkPermission();
        });


//        FpsMonitor fpsMonitor = new FpsMonitor();
//        fpsMonitor.startMonitor(value -> System.out.println("fps == " + value));


//        ITracer frameTrace = new FrameTrace();
//        IMonitor monitor = KYFpsMonitor.getMonitor();
//        KYFpsMonitor.getMonitor().init();
//        monitor.startMonitor();//开始监控

//        IMonitor memInfoMonitor = new MemInfoMonitor();
//        memInfoMonitor.startMonitor();//开始监控
//        frameTrace.startTrace();//开始采集
    }

    private void checkPermission() {
        try {
            String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            int permission = ActivityCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 1222);
            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
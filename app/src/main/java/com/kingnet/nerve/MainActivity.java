package com.kingnet.nerve;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.kingnet.nerve.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                Nerve nerve = new Nerve.Builder().setUploadDataType(3).create(MainActivity.this);

                nerve.startTrace();
            }
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
}
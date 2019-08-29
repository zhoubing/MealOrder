package com.demo.mealorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.demo.mealorder.websocket.ServerManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServerManager serverManager = new ServerManager();
        try {
            //https://stackoverflow.com/questions/2694797/bindexception-with-internet-permission-requested
            //Either root your phone, modify the firmware, or don't bind to ports lower than 1024.
            // That's a Linux thing more than an Android thing.
            serverManager.start(1029);
        } catch (IllegalStateException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}

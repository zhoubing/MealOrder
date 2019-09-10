package com.demo.mealorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.demo.mealorder.db.Database;
import com.demo.mealorder.db.ShopDao;
import com.demo.mealorder.nanohttpd.MyHttp;
import com.demo.mealorder.websocket.ServerManager;
import com.koushikdutta.async.http.server.AsyncHttpServer;

import java.io.IOException;

import static com.demo.mealorder.ktor.ServerKt.runServer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MainRecyclerViewAdapter mainRecyclerViewAdapter = new MainRecyclerViewAdapter();
        recyclerView.setAdapter(mainRecyclerViewAdapter);

        ServerManager serverManager = new ServerManager();
        try {
            //https://stackoverflow.com/questions/2694797/bindexception-with-internet-permission-requested
            //Either root your phone, modify the firmware, or don't bind to ports lower than 1024.
            // That's a Linux thing more than an Android thing.
            serverManager.start(1029);
        } catch (IllegalStateException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        ShopDao shopDao = Database.getDatabase().shopDao();
        shopDao.select().observe(this, shops -> {
            mainRecyclerViewAdapter.setData(shops);
            mainRecyclerViewAdapter.notifyDataSetChanged();
        });

        findViewById(R.id.add).setOnClickListener(v -> startActivity(new Intent(this, AddRecordActivity.class)));

        AsyncHttpServer server = new AsyncHttpServer();
        server.get("/", (request, response) -> response.send("Hello!"));
        server.listen(1030);

        try {
            new MyHttp();
        } catch (IOException e) {
            e.printStackTrace();
        }

        runServer();
    }


}

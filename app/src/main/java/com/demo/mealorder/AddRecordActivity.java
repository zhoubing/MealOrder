package com.demo.mealorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.mealorder.db.Database;
import com.demo.mealorder.db.Shop;
import com.demo.mealorder.db.ShopDao;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddRecordActivity extends AppCompatActivity {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Shop shop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record);
        shop = (Shop) getIntent().getSerializableExtra("record");

        EditText nameView = findViewById(R.id.name);
        EditText addressView = findViewById(R.id.address);
        EditText phoneView = findViewById(R.id.phone);
        if (shop == null) {
            shop = new Shop();
        }
        nameView.setText(shop.getName());
        addressView.setText(shop.getAddress());
        phoneView.setText(shop.getPhone());
        findViewById(R.id.create).setOnClickListener(v -> {
            ShopDao shopDao = Database.getDatabase().shopDao();
            Disposable disposable
                    = Completable.fromAction(() -> {
                        shop.setName(nameView.getText().toString());
                        shop.setAddress(addressView.getText().toString());
                        shop.setPhone(phoneView.getText().toString());
                        shopDao.insert(shop);
                    })
                    .subscribeOn(Schedulers.io()).subscribe(() ->
                            runOnUiThread(() -> {
                                        Toast.makeText(AddRecordActivity.this, "完成", Toast.LENGTH_LONG).show();
                                        finish();
                                    }), throwable ->
                                    Toast.makeText(AddRecordActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show());
            compositeDisposable.add(disposable);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}

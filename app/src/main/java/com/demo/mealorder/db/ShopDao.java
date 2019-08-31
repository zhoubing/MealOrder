package com.demo.mealorder.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ShopDao {

    @Insert(onConflict = REPLACE)
    long insert(Shop shop);

    @Query("SELECT * FROM shop")
    LiveData<List<Shop>> select();
}

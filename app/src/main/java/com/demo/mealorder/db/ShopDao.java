package com.demo.mealorder.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

@Dao
public interface ShopDao {

    @Insert
    void insert(Shop shop);
}

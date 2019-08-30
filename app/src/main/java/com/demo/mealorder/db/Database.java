package com.demo.mealorder.db;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.demo.mealorder.MainApp;

@android.arch.persistence.room.Database(entities = {Shop.class}, version = 1)
public abstract class Database extends RoomDatabase {
    private static volatile Database INSTANCE;

    public static Database getDatabase(){
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(MainApp.getInstance(),
                            Database.class, "database").build();
                }
            }
        }
        return INSTANCE;
    }

    abstract ShopDao shopDao();
}

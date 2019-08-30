package com.demo.mealorder.db;

public class ShopRepository {
    private ShopDao dao;

    public ShopRepository() {
        dao = Database.getDatabase().shopDao();
    }

    public void insertShop(Shop shop) {
        dao.insert(shop);
    }

}

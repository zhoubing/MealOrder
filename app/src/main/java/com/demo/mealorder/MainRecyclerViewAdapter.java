package com.demo.mealorder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.demo.mealorder.db.Shop;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewHolder> {
    private List<Shop> shops;

    @NonNull
    @Override
    public MainRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MainRecyclerViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.main_recyclerview_item,  viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerViewHolder viewHolder, int i) {
        viewHolder.update(shops.get(i));
    }

    @Override
    public int getItemCount() {
        return shops == null ? 0 : shops.size();
    }

    public void setData(List<Shop> shops) {
        this.shops = shops;
    }
}

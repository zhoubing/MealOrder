package com.demo.mealorder;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.demo.mealorder.db.Shop;

class MainRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView nameView;
    private TextView addressView;
    private TextView phoneView;

    public MainRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name_label);
        addressView = itemView.findViewById(R.id.address_label);
        phoneView = itemView.findViewById(R.id.phone_label);
    }

    public void update(Shop shop) {
        nameView.setText(shop.getName());
        addressView.setText(shop.getAddress());
        phoneView.setText(shop.getPhone());
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(itemView.getContext(), AddRecordActivity.class);
            intent.putExtra("record", shop);
            itemView.getContext().startActivity(intent);
        });
    }
}

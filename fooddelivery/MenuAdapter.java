package com.example.fooddelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private Context context;
    private List<MenuItem> menuItems;
    private double totalPrice = 0.0;

    public MenuAdapter(Context context, List<MenuItem> menuItems) {
        this.context = context;
        this.menuItems = menuItems;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.menu_item_layout, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder, int position) {
        final MenuItem menuItem = menuItems.get(position);
        holder.nameTextView.setText(menuItem.getName());
        holder.priceTextView.setText(String.format("$%.2f", menuItem.getPrice()));

        holder.orderButton.setOnClickListener(v -> {
            totalPrice += menuItem.getPrice();
            Toast.makeText(context, "Нарачано е: " + menuItem.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, priceTextView;
        Button orderButton;

        public MenuViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.menuItemName);
            priceTextView = itemView.findViewById(R.id.menuItemPrice);
            orderButton = itemView.findViewById(R.id.orderButton);
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}



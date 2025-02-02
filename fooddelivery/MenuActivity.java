package com.example.fooddelivery;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuItems;
    private Button finishOrderButton;
    private TextView totalPriceTextView;
    private double totalPrice = 0.0;

    private MenuItemDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        recyclerView = findViewById(R.id.recyclerView);
        finishOrderButton = findViewById(R.id.finishOrderButton);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);

        dbHelper = new MenuItemDatabaseHelper(this);

        menuItems = dbHelper.getAllMenuItems();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuAdapter = new MenuAdapter(this, menuItems);
        recyclerView.setAdapter(menuAdapter);

        finishOrderButton.setOnClickListener(v -> {
            totalPrice = menuAdapter.getTotalPrice();
            totalPriceTextView.setText(String.format("Вкупно цена: $%.2f", totalPrice));

            Toast.makeText(MenuActivity.this, "Нарачката е завршена! ", Toast.LENGTH_LONG).show();


        });
    }
}

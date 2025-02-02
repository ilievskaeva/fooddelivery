package com.example.fooddelivery;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText adminPassword, menuItemName, menuItemPrice;
    private Button adminLoginButton, addMenuItemButton, saveMenuItemButton;
    private MenuItemDatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminPassword = findViewById(R.id.adminPassword);
        adminLoginButton = findViewById(R.id.adminLoginButton);
        addMenuItemButton = findViewById(R.id.addMenuItemButton);
        menuItemName = findViewById(R.id.menuItemName);
        menuItemPrice = findViewById(R.id.menuItemPrice);
        saveMenuItemButton = findViewById(R.id.saveMenuItemButton);
        dbHelper = new MenuItemDatabaseHelper(this);

        adminLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = adminPassword.getText().toString();
                if (password.equals("admin1")) {
                    addMenuItemButton.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(AdminLoginActivity.this, "Погрешен пасворд!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        addMenuItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuItemName.setVisibility(View.VISIBLE);
                menuItemPrice.setVisibility(View.VISIBLE);
                saveMenuItemButton.setVisibility(View.VISIBLE);
            }
        });

        saveMenuItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = menuItemName.getText().toString();
                String priceString = menuItemPrice.getText().toString();

                if (name.isEmpty() || priceString.isEmpty()) {
                    Toast.makeText(AdminLoginActivity.this, "Ве молиме внесете име и цена на ставката!", Toast.LENGTH_SHORT).show();
                } else {
                    double price = Double.parseDouble(priceString);

                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(MenuItemDatabaseHelper.COLUMN_NAME, name);
                    values.put(MenuItemDatabaseHelper.COLUMN_PRICE, price);
                    long newRowId = db.insert(MenuItemDatabaseHelper.TABLE_NAME, null, values);

                    if (newRowId != -1) {
                        Toast.makeText(AdminLoginActivity.this, "Ставката е успешно додадена во менито!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AdminLoginActivity.this, "Грешка при додавање на ставка!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

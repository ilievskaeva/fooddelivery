package com.example.fooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UserLoginActivity extends FragmentActivity implements OnMapReadyCallback {

    private EditText userName, userPhone;
    private Button userLoginButton;
    private GoogleMap mMap;
    private LatLng userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        userName = findViewById(R.id.userName);
        userPhone = findViewById(R.id.userPhone);
        userLoginButton = findViewById(R.id.userLoginButton);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

        userLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String phone = userPhone.getText().toString();

                if (name.isEmpty() || phone.isEmpty() || userLocation == null) {
                    Toast.makeText(UserLoginActivity.this, "Ве молиме внесете ги сите податоци!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(UserLoginActivity.this, MenuActivity.class);
                    intent.putExtra("userName", name);
                    intent.putExtra("userPhone", phone);
                    intent.putExtra("userLocation", userLocation);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng skopje = new LatLng(41.9981, 21.4254);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(skopje, 12));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("Локација за достава"));
                userLocation = latLng;
            }
        });
    }
}

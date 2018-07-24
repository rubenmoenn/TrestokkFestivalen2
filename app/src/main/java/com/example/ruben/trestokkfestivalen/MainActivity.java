package com.example.ruben.trestokkfestivalen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mFestivalButton = (Button) findViewById(R.id.festival_button);
        Button mInfoButton = (Button) findViewById(R.id.info_button);
        Button mBillettButton = (Button) findViewById(R.id.billett_button);

        // festival_button is pressed
        mFestivalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Trestokk", "Festival Kart button Clicked");
                switchToMaps();
            }
        });

        // info_button is pressed
        mInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Trestokk", "Info button clicked");
                switchToInfo();
            }
        });

        // billett_button is pressed
        mBillettButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Trestokk", "Billett button is clicked");

                Uri uri = Uri.parse("https://trestokkfestivalen.no/shop/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    // Switches from MainActivity to MapsActivity via festival_button
    public void switchToMaps() {
        Intent intent = new Intent(this, com.example.ruben.trestokkfestivalen.MapsActivity.class);
        startActivity(intent);
    }

    // Switches from MainActivity to InfoActivity via info_button
    public void switchToInfo() {
        Intent intent = new Intent(this, com.example.ruben.trestokkfestivalen.InfoActivity.class);
        startActivity(intent);
    }






}

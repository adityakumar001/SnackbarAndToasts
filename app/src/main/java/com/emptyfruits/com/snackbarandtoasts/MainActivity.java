package com.emptyfruits.com.snackbarandtoasts;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button toastBtn, snackbarBtn, snackbarActionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        snackbarActionBtn = findViewById(R.id.snackbar_action);
        toastBtn = findViewById(R.id.toast_btn);
        snackbarBtn = findViewById(R.id.snackbar_btn);
        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Toast Created!!!",
                        Toast.LENGTH_SHORT).show();
            }
        });
        snackbarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v.getRootView(), "Snackbar created",
                        Snackbar.LENGTH_SHORT).show();
            }
        });

        snackbarActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm =
                        (ConnectivityManager) getApplicationContext().
                                getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null &&
                        activeNetwork.isConnectedOrConnecting();
                if (isConnected) {
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://www.google.com"));
                    startActivity(intent);
                } else {
                    Snackbar.make(v.getRootView(), "Offline!!Go to wifi settings",
                            Snackbar.LENGTH_LONG).setAction("Click here",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                    startActivity(intent);
                                }
                            }).show();
                }
            }
        });
    }


}

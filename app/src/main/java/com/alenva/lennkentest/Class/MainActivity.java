package com.alenva.lennkentest.Class;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alenva.lennkentest.R;
/*Alejandro Ensaztiga Vazquez*/
public class MainActivity extends AppCompatActivity {

private Button begin_Button;
private boolean access;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        access = isOnline(MainActivity.this);
        begin_Button= findViewById(R.id.begin_button);

        if (access) {
            begin_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, GasList.class);
                    startActivity(intent);
                    finish();

                }
            });
        }else{
            Toast.makeText(this, "Conectese a internet", Toast.LENGTH_SHORT).show();
            access = isOnline(MainActivity.this);
            finishAffinity();
        }

    }


    public static boolean isOnline(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    }


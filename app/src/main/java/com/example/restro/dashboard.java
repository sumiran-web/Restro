package com.example.restro;


import android.content.Intent;
import android.content.SharedPreferences;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;



public class dashboard extends AppCompatActivity {
    private Button button;
    ToggleButton toggle;
    TextView info;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);


        button = findViewById(R.id.upload);
         toggle =  findViewById(R.id.upload2);
         info = findViewById(R.id.information);
         logout= findViewById(R.id.logout);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    info.setVisibility(View.VISIBLE);
                    logout.setVisibility(View.VISIBLE);
                }

                else {
                info.setVisibility(View.INVISIBLE);
                logout.setVisibility(View.INVISIBLE);
                }
                }

        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    uploadActivity();

            }




        });

        logout.setOnClickListener(new View.OnClickListener() {
            private static final String TAG ="";

            @Override
            public void onClick(View arg0) {
                SharedPreferences myPrefs = getSharedPreferences("MY",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
                AppState.getSingleInstance().setLoggingOut(true);
                Log.d(TAG, "Now log out and start the activity login");
                Intent intent = new Intent(dashboard.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });


    }
    public void uploadActivity(){
        Intent intent = new Intent(this,uploadphoto.class);
        startActivity(intent);
    }





}

package com.example.restro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
private EditText username,password;
private Button login;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggle);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         String emailaddress = username.getText().toString().trim();
                String inputpassword = password.getText().toString().trim();

                if (TextUtils.isEmpty(emailaddress)){
                    Toast.makeText(MainActivity.this, "Plz enter e-mail address.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(inputpassword)){
                    Toast.makeText(MainActivity.this, "Plz enter password.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (inputpassword.length()<6){

                    Toast.makeText(MainActivity.this, "Password too short!!!", Toast.LENGTH_SHORT).show();
                }




                mAuth.signInWithEmailAndPassword(emailaddress, inputpassword)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),dashboard.class));

                                } else {
                                 Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });





            }
        });
    }

    public void register(View view) {

startActivity(new Intent(getApplicationContext(),register.class));
    }




    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "appliaction started successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "task of mainactivity ends", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Application stopped a while", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "end of use tasks", Toast.LENGTH_SHORT).show();
    }
}

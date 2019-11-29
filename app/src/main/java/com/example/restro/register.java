package com.example.restro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class register extends AppCompatActivity {
    private EditText emailadd, password, conform;
 private   Button signupbutton;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         emailadd = findViewById(R.id.email);
         password = findViewById(R.id.pass);
         conform = findViewById(R.id.conf);
         signupbutton = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();

        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailaddress = emailadd.getText().toString().trim();
                String inputpassword = password.getText().toString().trim();
                String conformpassword = conform.getText().toString().trim();

                if (TextUtils.isEmpty(emailaddress)){
                    Toast.makeText(register.this, "Plz enter e-mail address.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(inputpassword)){
                    Toast.makeText(register.this, "Plz enter password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(conformpassword)){
                    Toast.makeText(register.this, "Plz enter password", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (inputpassword.length()<6){

                    Toast.makeText(register.this, "Password too short!!!", Toast.LENGTH_SHORT).show();
                }

               if (inputpassword!=conformpassword){
                   Toast.makeText(register.this, "Password must be same!!!", Toast.LENGTH_SHORT).show();
               }

                if (inputpassword.equals(conformpassword)){
                    mAuth.createUserWithEmailAndPassword(emailaddress, inputpassword)
                            .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(register.this, "Registration succeffull", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(register.this, "Plz check connection.", Toast.LENGTH_SHORT).show();

                                    }

                                    // ...
                                }
                            });

                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "do your registration here", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "registration ends", Toast.LENGTH_SHORT).show();
    }
}

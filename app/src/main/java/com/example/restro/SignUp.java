package com.example.restro;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

EditText edit_Username;
    EditText edit_Email;
    EditText edit_Pass;
    Button btn_SignUp;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edit_Username = (EditText)findViewById(R.id.id_Username);
            edit_Email = (EditText)findViewById(R.id.id_Email);
        edit_Pass = (EditText)findViewById(R.id.id_Pass);
        btn_SignUp = (Button)findViewById(R.id.btn_SignUp);
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registeruser();

            }
        });


    }

    private void registeruser() {
        String name=edit_Username.getText().toString().trim().toLowerCase();
        String Email=edit_Email.getText().toString().trim().toLowerCase();
        String Pass=edit_Pass.getText().toString().trim().toLowerCase();

    }
}

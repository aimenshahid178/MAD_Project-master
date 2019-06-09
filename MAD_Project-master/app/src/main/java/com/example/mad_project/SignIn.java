package com.example.mad_project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    EditText enterEmail, enterPassword;
    Button signin;
    TextView signup;

    private SQLiteDatabase sql;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        myDb = new DatabaseHelper(this);

        enterEmail = (EditText) findViewById(R.id.lginemail);
        enterPassword = (EditText) findViewById(R.id.lginpwd);
        signin = (Button) findViewById(R.id.signinbtn);
        signup = (TextView) findViewById(R.id.gosignup);

        signin.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.signinbtn) {
            String email = enterEmail.getText().toString();
            String password = enterPassword.getText().toString();

            if (TextUtils.isEmpty(email)||TextUtils.isEmpty(password)) {
                Toast.makeText(SignIn.this, "Please fill both fields", Toast.LENGTH_LONG).show();
            }
            else {
                boolean exists = myDb.getUser(email, password);
                if(exists == true){
                    Intent homeintent = new Intent(SignIn.this,Home.class);
                    startActivity(homeintent);
                }
                else {
                    Toast.makeText(SignIn.this, "Invalid email or password", Toast.LENGTH_LONG).show();
                }
            }
        }

        else if(v.getId()==R.id.gosignup){
            Intent intent = new Intent(SignIn.this,SignUp.class);
            startActivity(intent);
        }
    }
}

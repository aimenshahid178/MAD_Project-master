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

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText editName, editEmail, editPassword;
    Button signup;
    TextView signin;

    private SQLiteDatabase sql;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.name);
        editEmail = (EditText) findViewById(R.id.email);
        editPassword = (EditText) findViewById(R.id.pwd);
        signup = (Button) findViewById(R.id.signupbtn);
        signin = (TextView) findViewById(R.id.gosignin);

        signup.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.signupbtn) {
            String name = editName.getText().toString();
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();

            if (TextUtils.isEmpty(name)) {
                Toast.makeText(SignUp.this, "Please enter name", Toast.LENGTH_LONG).show();
            }
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(SignUp.this, "Please enter email address", Toast.LENGTH_LONG).show();
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(SignUp.this, "Please enter password", Toast.LENGTH_LONG).show();
            }

            boolean isInserted = myDb.insertDataUser(name, email, password);
            if (isInserted == true) {
                Toast.makeText(SignUp.this, "You have successfully signed up", Toast.LENGTH_LONG).show();
                editName.setText("");
                editEmail.setText("");
                editPassword.setText("");
                Intent homeintent = new Intent(SignUp.this,Home.class);
                startActivity(homeintent);
            } else Toast.makeText(SignUp.this, "Sign Up was unsuccessful", Toast.LENGTH_LONG).show();
        } else if(v.getId()==R.id.gosignin){
            Intent intent = new Intent(SignUp.this,SignIn.class);
            startActivity(intent);
        }
    }
}

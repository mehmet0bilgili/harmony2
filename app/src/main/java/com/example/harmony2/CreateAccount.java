package com.example.harmony2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccount extends AppCompatActivity {

    EditText password,passwordAgain,email;

    TextView clickLogIn;
    Button createAccountBtn;
    ProgressBar progressBar;
    FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        setTitle("CREATE ACCOUNT");

        mAuth = FirebaseAuth.getInstance();

        email=findViewById(R.id.createAccountEmail);
        password=findViewById(R.id.createAccountPassword);
        passwordAgain=findViewById(R.id.createAccountPasswordAgain);
        createAccountBtn=findViewById(R.id.createAccountBtn);
        progressBar = findViewById(R.id.progressBar);
        clickLogIn = findViewById(R.id.logInNow);

        clickLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
                finish();
            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String tUsername, tEmail, tPassword, tPasswordAgain;
                tEmail = String.valueOf(email.getText());
                tPassword = String.valueOf(password.getText());
                tPasswordAgain = String.valueOf(passwordAgain.getText());

                if (TextUtils.isEmpty(tEmail)) {
                    Toast.makeText(CreateAccount.this,"Email shall not be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tPassword)) {
                    Toast.makeText(CreateAccount.this,"Password shall not be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tPasswordAgain)) {
                    Toast.makeText(CreateAccount.this,"Confirm password shall not be empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(tEmail, tPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(CreateAccount.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(CreateAccount.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
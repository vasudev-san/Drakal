package com.example.drakal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Register extends AppCompatActivity {
    EditText vFullName,vEmail,vPassword,vPhone;
    Button vRegister;
    TextView vLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar vProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        vFullName = findViewById(R.id.name);
        vEmail = findViewById(R.id.email);
        vPassword = findViewById(R.id.password);
        vPhone = findViewById(R.id.phone);
        vRegister = findViewById(R.id.login);
        vLoginBtn = findViewById(R.id.loginTxt);

        fAuth = FirebaseAuth.getInstance();
        vProgressBar = findViewById(R.id.Pg);

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        vRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = vEmail.getText().toString().trim();
                String password = vPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    vEmail.setError("Enter valid email");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    vPassword.setError("This Block can not be Empty");
                    return;
                }
                if(password.length() < 6){
                    vPassword.setError("Password must be grater than or Equal to atleast 6 characters");
                }

                vProgressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                        }
                        else
                        {
                            Toast.makeText(Register.this, "Error! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            vProgressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        vLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
}

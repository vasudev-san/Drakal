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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText vEmail,vPassword;
    Button vLoginBtn;
    TextView vCreatAccBtn;
    FirebaseAuth fAuth;
    ProgressBar vProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        vEmail = findViewById(R.id.email);
        vPassword = findViewById(R.id.password);
        vLoginBtn = findViewById(R.id.login);
        vCreatAccBtn = findViewById(R.id.goToManePage);
        vProgressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();

        vLoginBtn.setOnClickListener(new View.OnClickListener() {
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

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged In Succesfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Login.this, "Error! Wrong Password or Email.Please try again",Toast.LENGTH_LONG).show();
                            vProgressBar.setVisibility(View.GONE);
                        }

                    }
                });
                //FirebaseUser userRecord = FirebaseAuth.getInstance().getCurrentUser();
                //String UserId= userRecord.getUid();

            }
        });
        vCreatAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

    }
}
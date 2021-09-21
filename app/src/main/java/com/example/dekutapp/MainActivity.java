package com.example.dekutapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.dekutapp.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // init firebase instance
        firebaseAuth = FirebaseAuth.getInstance();

        //init the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Login here");
        actionBar.setDisplayShowHomeEnabled(true);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging In..");
        progressDialog.setCanceledOnTouchOutside(false);


        // intent to register activity
        binding.loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                //finish();
            }
        });

        //login users
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_login = binding.email.getText().toString().trim();
                String password_login = binding.password.getText().toString().trim();
                if (TextUtils.isEmpty(email_login)){
                    binding.email.setError("Please enter your email");
                    //Toast.makeText(getApplicationContext(), "Enter your email", Toast.LENGTH_SHORT).show();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(email_login).matches()){
                    binding.email.setError("Enter valid email");
                }
                if (TextUtils.isEmpty(password_login)){
                    binding.password.setError("Please enter your email");
                    //Toast.makeText(getApplicationContext(), "Enter your registration", Toast.LENGTH_SHORT).show();
                }
                else if (password_login.length()<6){
                    binding.password.setError("Password must be six characters");
                }
                firebaseAuth.signInWithEmailAndPassword(email_login,password_login).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        progressDialog.show();
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        firebaseUser.getEmail();
                        startActivity(new Intent(MainActivity.this, NewsActivity.class));
                        finish();
                        //Toast.makeText(getApplicationContext(), "Logged In" + email_login, Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
     }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
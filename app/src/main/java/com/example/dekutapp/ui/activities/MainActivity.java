package com.example.dekutapp.ui.activities;

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
        actionBar.setTitle("Register here");
        actionBar.setDisplayShowHomeEnabled(true);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging In..");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
        binding.login.setOnClickListener(view -> {
            String email_login = binding.email.getText().toString().trim();
            String password_login = binding.password.getText().toString().trim();
            if (TextUtils.isEmpty(email_login) && (!Patterns.EMAIL_ADDRESS.matcher(email_login).matches())){
                binding.email.setError("Please enter your email");
                binding.email.setError("Please enter valid email");
                binding.email.requestFocus();
            }
            if (TextUtils.isEmpty(password_login) && (password_login.length()<6)){
                binding.password.setError("Please enter your email");
                binding.password.setError("Password must be six characters");
                binding.password.requestFocus();

            }
            firebaseAuth.signInWithEmailAndPassword(email_login,password_login).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    progressDialog.show();
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    firebaseUser.getEmail();
                    startActivity(new Intent(MainActivity.this, NewsActivity.class));
                    finish();
                }
            }).addOnFailureListener(e -> {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
     }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
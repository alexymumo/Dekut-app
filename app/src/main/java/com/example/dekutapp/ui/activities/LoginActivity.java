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

import com.example.dekutapp.databinding.ActivityLoginBinding;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // init firebase instance
        firebaseAuth = FirebaseAuth.getInstance();

        // progressbar
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Logging In..");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register here");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();

        binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, ResetPassword.class);
                startActivity(intent);
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        binding.loginTextView.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), RegisterActivity.class)));
        binding.login.setOnClickListener(view -> {
            String email_login = binding.email.getText().toString().trim();
            String password_login = binding.password.getText().toString().trim();
            if (TextUtils.isEmpty(email_login) && (!Patterns.EMAIL_ADDRESS.matcher(email_login).matches())){
                binding.email.setError("Required");
                binding.email.requestFocus();
            }
            if (TextUtils.isEmpty(password_login) && (password_login.length()<6)){
                binding.password.setError("Required");
                binding.password.requestFocus();
            }
            firebaseAuth.signInWithEmailAndPassword(email_login,password_login).addOnSuccessListener(authResult -> {
                //progressDialog.show();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                firebaseUser.getEmail();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }).addOnFailureListener(e -> {
                //progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }

    private void loginUser() {
  //FirebaseAuth.getInstance().getCurrentUser().getUid();
                     //firebaseAuth
    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {

    }
}
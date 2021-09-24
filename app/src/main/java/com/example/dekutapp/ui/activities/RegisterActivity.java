package com.example.dekutapp.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dekutapp.R;
import com.example.dekutapp.data.models.User;
import com.example.dekutapp.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding binding;
    private FirebaseAuth firebaseAuth;
    private EditText email, regno, name, course, year, password;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // init  firebase instance
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        regno = findViewById(R.id.regno);
        name = findViewById(R.id.name);
        course = findViewById(R.id.course);
        year = findViewById(R.id.year);
        password = findViewById(R.id.password);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Register here");
        actionBar.setDisplayShowHomeEnabled(true);
        //actionBar.show();

        //progress bar configuration
       progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Creating your account");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
       // progressDialog.show();

        binding.registerUser.setOnClickListener(this);
        binding.registerTextView.setOnClickListener(this);

        binding.registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
        // onclick for registering users
        binding.registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }
    private void registerUser() {
        String email_text = email.getText().toString().trim();
        String password_text = password.getText().toString().trim();
        String name_text = name.getText().toString().trim();
        String course_text = course.getText().toString().trim();
        String year_text = year.getText().toString().trim();
        String reg_text = regno.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()) {
            binding.email.setError("Invalid email format");
            binding.email.requestFocus();
        } else if (TextUtils.isEmpty(name_text)) {
            binding.course.setError("Enter your course");
            binding.course.requestFocus();
        } else if (TextUtils.isEmpty(course_text)) {
            binding.name.setError("Enter your name");
            binding.name.requestFocus();
        } else if (TextUtils.isEmpty(year_text)) {
            binding.year.setError("Enter year of study");
            binding.year.requestFocus();
        } else if (TextUtils.isEmpty(password_text) && password_text.length() >= 6) {
            binding.password.setError("Enter your password");
            binding.password.requestFocus();
        } else if (TextUtils.isEmpty(reg_text)) {
            binding.regno.setError("Registration required");
            binding.regno.requestFocus();
        }
        firebaseAuth.createUserWithEmailAndPassword(email_text, password_text)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User();

                            //User user = new User(reg_text, email_text, password_text, course_text, name_text);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Registered User successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                        progressDialog.show();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View view) {
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}


  /*init the action bar
   ;*/
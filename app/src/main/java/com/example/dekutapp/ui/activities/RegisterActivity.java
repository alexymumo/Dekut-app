package com.example.dekutapp.ui.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dekutapp.R;
import com.example.dekutapp.model.User;
import com.example.dekutapp.databinding.ActivityRegisterBinding;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.shashank.sony.fancytoastlib.FancyToast;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding binding;
    private FirebaseAuth firebaseAuth;
    EditText email, regno, name, course, year, password;
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

        //actionBar.show();

        //progress bar configuration
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Creating your account");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login here");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.show();

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
        String email_text = binding.email.getText().toString().trim();
        String password_text = binding.password.getText().toString().trim();
        String name_text = binding.name.getText().toString().trim();
        String course_text = binding.course.getText().toString().trim();
        String year_text = binding.year.getText().toString().trim();
        String reg_text = binding.regno.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email_text).matches()) {
            binding.email.setError("Invalid email format");
            binding.email.requestFocus();
        }
        if (TextUtils.isEmpty(name_text)) {
            binding.course.setError("Required!!");
            binding.course.requestFocus();
        }
        if (TextUtils.isEmpty(course_text)) {
            binding.name.setError("Required!!");
            binding.name.requestFocus();
        }
        if (TextUtils.isEmpty(year_text)) {
            binding.year.setError("Required!!");
            binding.year.requestFocus();
        }
        if (TextUtils.isEmpty(password_text) && password_text.length() >= 6) {
            binding.password.setError("Required!!");
            binding.password.requestFocus();
        }
        if (TextUtils.isEmpty(reg_text)) {
            binding.regno.setError("Required");
            binding.regno.requestFocus();
        }
        firebaseAuth.createUserWithEmailAndPassword(email_text, password_text)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        User user = new User(reg_text, email_text, password_text, course_text, name_text);
                        FirebaseDatabase.getInstance().getReference("Users")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(task1 -> {
                                    if (task1.isSuccessful()) {
                                        FancyToast.makeText(this, "Registered successfully", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS, true);
                                        //Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                        progressDialog.show();
                                    } else {
                                        //Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                        FancyToast.makeText(this, "Registration failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true);
                                        //progressDialog.dismiss();
                                    }
                                });
                    } else {
                        FancyToast.makeText(this, "Registration failed", FancyToast.LENGTH_SHORT, FancyToast.ERROR, true);
                        //progressDialog.dismiss();
                        //Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
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

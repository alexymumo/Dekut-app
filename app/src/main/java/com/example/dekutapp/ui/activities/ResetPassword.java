package com.example.dekutapp.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.example.dekutapp.databinding.ActivityResetPasswordBinding;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {

    private ActivityResetPasswordBinding binding;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();

        binding.sendreset.setOnClickListener(view -> {
            String email_text = binding.usermail.getText().toString().trim();

            if (TextUtils.isEmpty(email_text)){
                binding.usermail.setError("Required");
                binding.usermail.requestFocus();
            }
            firebaseAuth.sendPasswordResetEmail(email_text).addOnCompleteListener(task -> {
                if (task.isSuccessful()){

                    Toast.makeText(ResetPassword.this, "Email send", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ResetPassword.this, "Email not send", Toast.LENGTH_SHORT).show();
                }

            });

        });

    }
}
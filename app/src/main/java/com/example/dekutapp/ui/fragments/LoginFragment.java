package com.example.dekutapp.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dekutapp.R;
import com.example.dekutapp.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FragmentLoginBinding binding;
   // NavController navController = Navigation.findNavController(view);

    public LoginFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){

                }

            }
        };
        return inflater.inflate(R.layout.fragment_login, container, false);

    }

    private void loginUser() {
        firebaseAuth = FirebaseAuth.getInstance();
        String email_login = binding.email.getText().toString();
        String password_login = binding.password.getText().toString();
        if (TextUtils.isEmpty(email_login) && (!Patterns.EMAIL_ADDRESS.matcher(email_login).matches())){
            binding.email.setError("Required");
            binding.email.requestFocus();
        }
        if (TextUtils.isEmpty(password_login) && (password_login.length()<6)){
            binding.password.setError("Required");
            binding.password.requestFocus();
        }

        firebaseAuth.signInWithEmailAndPassword(email_login, password_login).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}
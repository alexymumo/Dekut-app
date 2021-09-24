package com.example.dekutapp.ui.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.dekutapp.data.models.User;
import com.example.dekutapp.databinding.ActivitySplashBinding;
import com.example.dekutapp.viewmodel.SplashViewModel;

public class SplashActivity extends AppCompatActivity {
    private SplashViewModel splashViewModel;
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViewModel();
        verifyUserAuthentication();

        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void verifyUserAuthentication() {
        splashViewModel.verifyUserAuthentication();
        splashViewModel.isUserAuthenticatedLiveData.observe(this, new Observer<User>(){
            @Override
            public void onChanged(User user){
                if (user.isAuthenticated){
                    moveToMainActivity();
                }
                else{
                    goToRegisterActivity();
                }
                finish();
            }
        });
    }

    private void goToRegisterActivity() {
        Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void moveToMainActivity() {
        Intent intent = new Intent(SplashActivity.this, NewsActivity.class);
        startActivity(intent);
    }

    private void initViewModel(){
        splashViewModel = new ViewModelProvider(this, new
                /*use getApplication if using activity
                * and getContextMenu when using fragments */
                ViewModelProvider.AndroidViewModelFactory(getApplication())).get(SplashViewModel.class);
    }
}
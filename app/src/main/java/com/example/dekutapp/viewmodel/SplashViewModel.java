package com.example.dekutapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.dekutapp.data.models.User;
import com.example.dekutapp.repositories.SplashRepository;


public class SplashViewModel extends AndroidViewModel {
    private SplashRepository splashRepository;
    public LiveData<User> isUserAuthenticatedLiveData;
    public SplashViewModel(@NonNull Application application) {
        super(application);
        splashRepository = new SplashRepository();
    }

    public void verifyUserAuthentication() {
        isUserAuthenticatedLiveData = splashRepository.verifyUserAuthentication();
    }
}

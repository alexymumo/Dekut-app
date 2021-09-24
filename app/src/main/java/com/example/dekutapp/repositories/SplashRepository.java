package com.example.dekutapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dekutapp.data.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashRepository {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private CollectionReference userRef = FirebaseFirestore.getInstance().collection(User.keyTableName);
    private User user = new User();
    public MutableLiveData<User> verifyUserAuthentication() {
        MutableLiveData<User> isUserAuthenticatedMutableLiveData = new MutableLiveData<>();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null){
            user.isAuthenticated = false;
            isUserAuthenticatedMutableLiveData.setValue(user);
        }
        else {
            user.isAuthenticated = true;
        }
        isUserAuthenticatedMutableLiveData.setValue(user);
        return isUserAuthenticatedMutableLiveData;

    }

}

package com.example.dekutapp.repositories;

public class SplashRepository {
    /*private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private User user= new User();
    CollectionReference collectionReference = firebaseFirestore.collection("Users");
    private String uid;
    //private String uid;

    public MutableLiveData<User> checkIfUserIsAuthenticatedInFirebase() {
        MutableLiveData<User> isUserAuthenticatedInFirebaseLiveData = new MutableLiveData<>();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null){
            user.isAuthenticated = false;
        }
        else {
            user.uid = firebaseUser.getUid();
            user.isAuthenticated = true;
        }
        isUserAuthenticatedInFirebaseLiveData.setValue(user);
        return isUserAuthenticatedInFirebaseLiveData;
    }
    public MutableLiveData<User> addUserToLiveData() {
        MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
        collectionReference.document(uid).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                DocumentSnapshot documentSnapshot = task.getResult();
                if (documentSnapshot.exists()){
                    User user = documentSnapshot.toObject(User.class);
                    userMutableLiveData.setValue(user);
                }
            }else {
                Log.d("TAG", task.getException().getMessage());
            }
        });
        return userMutableLiveData;

    }*/
}

package com.example.dekutapp.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dekutapp.R;

public class SplashFragment extends Fragment {


    public SplashFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // NavController navController = Navigation.findNavController(getView());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

              // navController.navigate(R.id.splashFragment);
            }
        }, 3000);
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }
}
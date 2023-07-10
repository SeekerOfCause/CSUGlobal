package com.example.trafficsigntest.helpers;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.example.trafficsigntest.components.User;

public class UserDataManager extends Application {

    private static SharedPreferences sharedPrefs;

    // Function to save user data
    public static void saveUserData(String userName, int score, View v) {
        getPrefs(v);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(userName, score);
        editor.apply();
    }

    // Function to load user data
    public static User loadUserData(String name, View v) {
        getPrefs(v);
        int score = sharedPrefs.getInt(name, 0);
        return new User(name, score);
    }

    // Function to clear user data
//    public static void clearUserData(String name) {
//        SharedPreferences.Editor editor = TestFragment.getSharedPreferences().edit();
//        editor.remove(name);
//        editor.apply();
//    }

    public static boolean checkForUser(String name, View v) {
        getPrefs(v);
        return sharedPrefs.contains(name);
    }

    private static void getPrefs(View v) {
        sharedPrefs = v.getContext().getSharedPreferences("Users", Context.MODE_PRIVATE);
    }
}

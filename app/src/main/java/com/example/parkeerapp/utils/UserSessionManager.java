package com.example.parkeerapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

public class UserSessionManager {
    private static final String PREF_NAME = "ParkeerPrefs";
    public static final String KEY_EMAIL = "user_email";
    private static final String KEY_FULLNAME = "user_fullname";
    private static final String KEY_PHONE = "user_phone";
    private static final String KEY_PASSWORD = "user_password"; // Optional
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public UserSessionManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void createLoginSession(String email, String fullname, String phone, String password) {
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_FULLNAME, fullname);
        editor.putString(KEY_PHONE, phone);
        editor.putString(KEY_PASSWORD, password); // Optional
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.apply();

        Log.d("SESSION", "Menyimpan email: " + email);
    }

    public String getEmail() {
        return pref.getString(KEY_EMAIL, null);
    }

    public String getFullname() {
        return pref.getString(KEY_FULLNAME, null);
    }

    public String getPhone() {
        return pref.getString(KEY_PHONE, null);
    }

    public String getPassword() {
        return pref.getString(KEY_PASSWORD, null);
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void clearSession() {
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
    }


    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
        user.put(KEY_FULLNAME, pref.getString(KEY_FULLNAME, null));
        user.put(KEY_PHONE, pref.getString(KEY_PHONE, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        return user;
    }


}

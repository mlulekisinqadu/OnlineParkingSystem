package com.bryansinqadu.eparking_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DefaultSettings {
    private static SharedPreferences sharedPreferences;


    private static void getSharedPreferencesInstance(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean splashScreenEnabled(Context context) {
        getSharedPreferencesInstance(context);
        return sharedPreferences.getBoolean("splash", true);

    }


    public static boolean notificationEnanbled(Context context) {
        getSharedPreferencesInstance(context);
        return sharedPreferences.getBoolean("notifications", true);
    }


    public static String getListPrefereceValue(Context context) {
        getSharedPreferencesInstance(context);
        return sharedPreferences.getString("listPreference", ""); // there was no default value, we can leave default value empty
    }

    public static String getUserPassword(Context context) {
        getSharedPreferencesInstance(context);
        return sharedPreferences.getString("password", "");
    }
}

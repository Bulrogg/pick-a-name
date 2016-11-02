package fr.fmi.pickaname.app.storage;

import android.content.SharedPreferences;

import fr.fmi.pickaname.core.storage.DeviceStorage;


public class DeviceStorageImpl implements DeviceStorage {

    private final SharedPreferences sharedPreferences;

    public DeviceStorageImpl(final SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public String get(final String key, final String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    @Override
    public void put(final String key, final String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

}

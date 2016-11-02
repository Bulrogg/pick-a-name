package fr.fmi.pickaname.core.storage;

public interface DeviceStorage {

    String get(String key, String defaultValue);

    void put(String key, String value);

}

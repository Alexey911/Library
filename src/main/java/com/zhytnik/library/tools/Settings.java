package com.zhytnik.library.tools;

import java.util.ResourceBundle;

public class Settings {
    private static Settings instance = new Settings();
    private ResourceBundle bundleSetting;

    private Settings() {
        bundleSetting = ResourceBundle.getBundle("app");
    }

    public static Settings getInstance() {
        return instance;
    }

    public String getString(String name) {
        return bundleSetting.getString(name);
    }

    public Integer getInteger(String name) {
        return Integer.valueOf(bundleSetting.getString(name));
    }
}
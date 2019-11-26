package com.ismaeld.RNBuildConfig;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RNBuildConfigModule extends ReactContextBaseJavaModule {
    private Class buildConfigClass;
    private String NAME = "RNBuildConfig";
    private final Map<String, Object> constants = new HashMap<>();

    public RNBuildConfigModule(ReactApplicationContext reactContext, Class buildConfigClass) {
        super(reactContext);

        this.buildConfigClass = buildConfigClass;

        if(constants.isEmpty()) {
            getConstants();
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public String getFlavor() {

        try {

            return this.constants.get("FLAVOR").toString();

        } catch (Exception e) {

            return null;

        }

    }

    @ReactMethod
    public String getVersionName() {

        try {

            return this.constants.get("VERSION_NAME").toString();

        } catch (Exception e) {

            return null;

        }

    }

    @ReactMethod
    public String getVersionCode() {

        try {

            return this.constants.get("VERSION_CODE").toString();

        } catch (Exception e) {

            return null;

        }

    }

    @ReactMethod
    public Map<String, Object> getConfig() {
        return this.constants;
    }

    @Override
    public Map<String, Object> getConstants() {

        Field[] fields = buildConfigClass.getDeclaredFields();
        for (Field f : fields) {
            try {
                this.constants.put(f.getName(), f.get(null));
            } catch (IllegalAccessException e) {
                Log.d(NAME, "Could not access BuildConfig field " + f.getName());
            }
        }

        return this.constants;

    }
}

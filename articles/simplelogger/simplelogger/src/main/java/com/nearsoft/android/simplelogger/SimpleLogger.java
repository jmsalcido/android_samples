package com.nearsoft.android.simplelogger;

import android.util.Log;

public class SimpleLogger {

    public static SimpleLogger getLogger() {
        return new SimpleLogger();
    }

    public void print(String message) {
        Log.v(getClass().getSimpleName(), message);
    }

}

package com.androiddevs;

import android.util.Log;

/**
 * Created by ADMIN on 26-Dec-17.
 */

public class LogsUtils {
    private static boolean PRINT_LOGS = true;
    public static void printLog(String mTag, String mMessage) {
        if (PRINT_LOGS){
            Log.e(mTag, ">>"+mMessage);
        }
    }
}

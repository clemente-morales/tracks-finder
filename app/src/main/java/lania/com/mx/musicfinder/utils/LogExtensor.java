package lania.com.mx.musicfinder.utils;

import android.util.Log;

/**
 * Created by clerks on 9/9/15.
 */
public class LogExtensor {
    private static final String TAG = LogExtensor.class.getSimpleName();

    private static final int MAXIMO_CARACTERES_DESPLIEGE_LOGCAT = 4000;

    private LogExtensor() {
    }

    public static void imprimeLog(String cadena) {
        if (cadena.length() < MAXIMO_CARACTERES_DESPLIEGE_LOGCAT) {
            Log.d(TAG, cadena);
        } else {
            Log.d(TAG, cadena.substring(0, MAXIMO_CARACTERES_DESPLIEGE_LOGCAT));
            imprimeLog(cadena.substring(MAXIMO_CARACTERES_DESPLIEGE_LOGCAT, cadena.length()));
        }
    }
}

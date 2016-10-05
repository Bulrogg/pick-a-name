package fr.android.fmi.pickaname.utils;

import android.util.Log;

import javax.inject.Inject;

public class Logger {

    @Inject
    public Logger() {
    }

    public void error(final Class<?> clazz, final String msg, final Throwable throwable) {
        log(clazz, LogLevel.ERROR, msg, throwable);
    }

    public void warning(final Class<?> clazz, final String msg) {
        log(clazz, LogLevel.WARN, msg, null);
    }

    public void info(final Class<?> clazz, final String msg) {
        log(clazz, LogLevel.INFO, msg, null);
    }

    public void debug(final Class<?> clazz, final String msg) {
        log(clazz, LogLevel.DEBUG, msg, null);
    }

    public void wtf(final Class<?> clazz, final String msg) {
        log(clazz, LogLevel.WTF, msg, null);
    }

    private void log(
            final Class<?> clazz, final LogLevel level, final String msg, final Throwable throwable
    ) {
        final String tag = clazz.getName();
        switch (level) {
            case ERROR:
                Log.e(tag, msg, throwable);
                break;
            case WARN:
                Log.w(tag, msg, throwable);
                break;
            case INFO:
                Log.i(tag, msg, throwable);
                break;
            case DEBUG:
                Log.d(tag, msg, throwable);
                break;
            case WTF:
            default:
                Log.wtf(tag, msg, throwable);
        }

    }

    private enum LogLevel {
        ERROR,
        WARN,
        INFO,
        DEBUG,
        WTF
    }
}

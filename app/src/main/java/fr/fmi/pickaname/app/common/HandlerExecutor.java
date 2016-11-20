package fr.fmi.pickaname.app.common;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;

public class HandlerExecutor implements Executor {

    private final Handler handler;

    public HandlerExecutor() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(final Runnable command) {
        handler.post(command);
    }

}

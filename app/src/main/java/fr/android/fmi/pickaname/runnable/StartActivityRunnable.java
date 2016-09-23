package fr.android.fmi.pickaname.runnable;

import android.app.Activity;
import android.content.Intent;

import java.lang.ref.WeakReference;

public class StartActivityRunnable implements Runnable {

    private final WeakReference<Activity> weakReference;
    private final Intent intent;

    public StartActivityRunnable(final Activity activity, final Intent intent) {
        this.intent = intent;
        this.weakReference = new WeakReference<>(activity);
    }

    @Override
    public void run() {
        final Activity activity = weakReference.get();
        if (activity != null && !activity.isFinishing()) {
            activity.startActivity(intent);
            activity.finish();
        }
    }
}

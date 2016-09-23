package fr.android.fmi.pickaname.view.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import fr.android.fmi.pickaname.R;
import fr.android.fmi.pickaname.runnable.StartActivityRunnable;
import fr.android.fmi.pickaname.view.main.MainActivity;

public class SplashActivity extends AbstractBaseActivity {

    private static final int SPLASH_DURATION = 1000;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent nextActivity = MainActivity.newIntent(this);
        new Handler().postDelayed(new StartActivityRunnable(this, nextActivity), SPLASH_DURATION);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

}

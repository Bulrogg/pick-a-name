package fr.android.fmi.pickaname.view.splash;

import android.os.Bundle;

import fr.android.fmi.pickaname.R;
import fr.android.fmi.pickaname.view.main.MainActivity;

public class SplashActivity extends AbstractBaseActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO erreur
            e.printStackTrace();
        }

        startActivity(MainActivity.newIntent(this));
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

}

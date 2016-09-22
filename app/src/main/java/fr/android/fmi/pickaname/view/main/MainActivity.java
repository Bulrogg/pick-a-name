package fr.android.fmi.pickaname.view.main;

import android.content.Context;
import android.content.Intent;

import fr.android.fmi.pickaname.R;
import fr.android.fmi.pickaname.view.splash.AbstractBaseActivity;

public class MainActivity extends AbstractBaseActivity {

    public static Intent newIntent(final Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override protected int getLayoutId() {
        return R.layout.activity_main;
    }

}

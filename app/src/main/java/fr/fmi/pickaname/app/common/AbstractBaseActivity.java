package fr.fmi.pickaname.app.common;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

public abstract class AbstractBaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        bindView();
        injectDependencies();
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    protected abstract void injectDependencies();

    protected void bindView() {
        ButterKnife.bind(this);
    }
}

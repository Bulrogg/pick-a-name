package fr.fmi.pickaname.app.common;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class AbstractMainFragment extends Fragment {

    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState
    ) {
        final View view = inflater.inflate(getLayoutResId(), container, false);
        bindView(view);
        injectDependencies();
        init();
        return view;
    }

    public abstract
    @StringRes
    int getTitleId();

    public abstract
    @LayoutRes
    int getLayoutResId();

    protected abstract void injectDependencies();

    // TODO ooo remove
    @Deprecated
    public abstract void init();

    protected void bindView(final View view) {
        ButterKnife.bind(this, view);
    }

}

package fr.fmi.pickaname.app.common;

import android.app.Fragment;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;

public abstract class AbstractMainFragment extends Fragment {

    public abstract @StringRes int getTitleId();

    public abstract @LayoutRes int getLayoutId();

}

package fr.fmi.pickaname.app.about;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;

public class AboutFragment extends AbstractMainFragment {

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override public int getTitleId() {
        return R.string.fragment_about_title;
    }

    @Override public int getLayoutId() {
        return R.layout.fragment_about;
    }
}

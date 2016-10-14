package fr.fmi.pickaname.app.accepted;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;

public class AcceptedFragment extends AbstractMainFragment {

    public static AcceptedFragment newInstance() {
        return new AcceptedFragment();
    }

    @Override public int getTitleId() {
        return R.string.fragment_accepted_title;
    }

    @Override public int getLayoutId() {
        return R.layout.fragment_accepted;
    }
}

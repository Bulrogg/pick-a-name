package fr.fmi.pickaname.app.rejected;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;

public class RejectedFragment extends AbstractMainFragment {

    public static RejectedFragment newInstance() {
        return new RejectedFragment();
    }

    @Override public int getTitleId() {
        return R.string.fragment_rejected_title;
    }

    @Override public int getLayoutId() {
        return R.layout.fragment_rejected;
    }
}

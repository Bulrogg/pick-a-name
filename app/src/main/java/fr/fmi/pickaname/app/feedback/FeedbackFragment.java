package fr.fmi.pickaname.app.feedback;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;

public class FeedbackFragment extends AbstractMainFragment {

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    @Override public int getTitleId() {
        return R.string.fragment_feedback_title;
    }

    @Override public int getLayoutId() {
        return R.layout.fragment_feedback;
    }
}

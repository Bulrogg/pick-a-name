package fr.fmi.pickaname.app.feedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.common.AbstractMainFragment;

public class FeedbackFragment extends AbstractMainFragment {

    public static FeedbackFragment newInstance() {
        return new FeedbackFragment();
    }

    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override public int getTitleId() {
        return R.string.fragment_feedback_title;
    }

    @Override public int getLayoutId() {
        return R.layout.fragment_feedback;
    }
}

package fr.fmi.pickaname.app.rejected;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;

public class RejectedFragment extends AbstractMainFragment {

    public static RejectedFragment newInstance() {
        return new RejectedFragment();
    }

    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }
    @Override public int getTitleId() {
        return R.string.fragment_rejected_title;
    }

    @Override public int getLayoutId() {
        return R.layout.fragment_rejected;
    }
}

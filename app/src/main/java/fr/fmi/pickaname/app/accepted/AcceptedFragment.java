package fr.fmi.pickaname.app.accepted;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;

public class AcceptedFragment extends AbstractMainFragment {

    public static AcceptedFragment newInstance() {
        return new AcceptedFragment();
    }

    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public int getTitleId() {
        return R.string.fragment_accepted_title;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_accepted;
    }
}

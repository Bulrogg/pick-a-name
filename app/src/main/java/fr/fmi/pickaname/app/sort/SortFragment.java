package fr.fmi.pickaname.app.sort;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;

public class SortFragment extends AbstractMainFragment {

    public static SortFragment newInstance() {
        return new SortFragment();
    }

    @Override
    public int getTitleId() {
        return R.string.fragment_sort_title;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sort;
    }
}

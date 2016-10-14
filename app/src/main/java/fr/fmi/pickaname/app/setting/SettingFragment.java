package fr.fmi.pickaname.app.setting;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;

public class SettingFragment extends AbstractMainFragment {

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override public int getTitleId() {
        return R.string.fragment_setting_title;
    }

    @Override public int getLayoutId() {
        return R.layout.fragment_setting;
    }
}

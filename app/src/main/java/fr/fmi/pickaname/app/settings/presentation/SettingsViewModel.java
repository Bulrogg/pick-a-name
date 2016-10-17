package fr.fmi.pickaname.app.settings.presentation;

import android.support.annotation.StringRes;

public class SettingsViewModel {

    public static final int VF_LOADING = 0;
    public static final int VF_SUCCESS = 1;
    public static final int VF_ERROR = 2;

    public int displayedChild;

    @StringRes
    public int errorResId;

    public String lastName;

    public String gender;
}

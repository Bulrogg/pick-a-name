package fr.fmi.pickaname.app.settings.presentation;

import android.databinding.BaseObservable;

public class SettingsScreenViewModel extends BaseObservable {

    public static final int VF_LOADING = 0;
    public static final int VF_SUCCESS = 1;
    public static final int VF_ERROR = 2;

    public int displayedChild;

    public String lastName;

    public String researchType;
}

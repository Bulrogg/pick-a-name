package fr.fmi.pickaname.app.sort.presentation;

import android.support.annotation.StringRes;

public class SortViewModel {

    public static final int VF_SORT_LOADING = 0;
    public static final int VF_SORT_SUCCESS = 1;
    public static final int VF_SORT_ERROR = 2;

    public int displayedChild;

    public boolean shouldHideKeyboard;

    @StringRes
    public int errorResId;

    public String firstName;

}

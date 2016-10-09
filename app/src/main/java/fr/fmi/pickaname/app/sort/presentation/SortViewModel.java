package fr.fmi.pickaname.app.sort.presentation;

import android.support.annotation.StringRes;

public class SortViewModel {

    public static final int DISPLAY_FORM = 0;
    public static final int DISPLAY_LOADING = 1;
    public static final int DISPLAY_SUCCESS = 2;

    public int displayedChild;

    public boolean shouldHideKeyboard;

    public String title;

    public String description;

    @StringRes
    public int errorResId;
}

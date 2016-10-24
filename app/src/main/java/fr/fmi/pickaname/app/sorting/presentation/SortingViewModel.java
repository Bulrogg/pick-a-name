package fr.fmi.pickaname.app.sorting.presentation;

import android.support.annotation.StringRes;

public class SortingViewModel {

    public static final int VF_SORT_LOADING = 0;
    public static final int VF_SORT_SUCCESS = 1;
    public static final int VF_SORT_ERROR = 2;

    public int displayedChild;

    @StringRes
    public int errorResId;

    public String lastName;

}

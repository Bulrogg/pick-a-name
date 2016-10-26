package fr.fmi.pickaname.app.sorting.presentation;

import android.support.annotation.StringRes;

public class SortingViewModel {

    static final int VF_LOADING = 0;
    static final int VF_SUCCESS = 1;
    static final int VF_ERROR = 2;
    static final int VF_NO_MORE_FIRST_NAME = 3;

    public int displayedChild;

    @StringRes
    public int errorResId;

    public String lastName;

}

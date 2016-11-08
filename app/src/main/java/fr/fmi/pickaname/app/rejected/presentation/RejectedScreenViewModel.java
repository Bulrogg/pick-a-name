package fr.fmi.pickaname.app.rejected.presentation;

import android.databinding.BaseObservable;

public class RejectedScreenViewModel extends BaseObservable {

    static final int VF_LOADING = 0;
    static final int VF_SUCCESS = 1;
    static final int VF_ERROR = 2;
    static final int VF_NO_FIRST_NAME_REJECTED = 3;

    public int displayedChild;

}

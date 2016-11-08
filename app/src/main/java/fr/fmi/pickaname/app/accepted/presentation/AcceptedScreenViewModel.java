package fr.fmi.pickaname.app.accepted.presentation;

import android.databinding.BaseObservable;

public class AcceptedScreenViewModel extends BaseObservable {

    static final int VF_LOADING = 0;
    static final int VF_SUCCESS = 1;
    static final int VF_ERROR = 2;
    static final int VF_NO_FIRST_NAME_ACCEPTED = 3;

    public int displayedChild;

}

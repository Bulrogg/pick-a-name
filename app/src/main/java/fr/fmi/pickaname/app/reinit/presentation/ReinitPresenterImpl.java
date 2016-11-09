package fr.fmi.pickaname.app.reinit.presentation;

import android.content.Context;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.core.reinit.ReinitPresenter;

public class ReinitPresenterImpl implements ReinitPresenter {

    private final ReinitView view;
    private final Context context;

    public ReinitPresenterImpl(final ReinitView view, final Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void presentReinitializationSuccess() {
        view.displayMessage(context.getString(R.string.fragment_reinit_success));
    }

    @Override
    public void presentReinitializationFailure() {
        view.displayMessage(context.getString(R.string.fragment_reinit_failed));
    }
}

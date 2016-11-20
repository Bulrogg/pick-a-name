package fr.fmi.pickaname.app.reinit;

import android.widget.Toast;

import javax.inject.Inject;

import butterknife.OnClick;
import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.common.AbstractMainFragment;
import fr.fmi.pickaname.app.reinit.controller.ReinitController;
import fr.fmi.pickaname.app.reinit.presentation.ReinitView;

public class ReinitFragment extends AbstractMainFragment implements ReinitView {

    @Inject ReinitController controller;

    public static ReinitFragment newInstance() {
        return new ReinitFragment();
    }

    @Override
    public int getTitleId() {
        return R.string.fragment_reinit_title;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_reinit;
    }

    @Override
    protected void injectDependencies() {
        ReinitComponent.Initializer.init(this).inject(this);
    }

    @Override
    public void init() {
    }

    @Override
    public void displayMessage(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.reinit_button)
    public void reinitClickHandler() {
        controller.reinitialize();
    }
}

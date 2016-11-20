package fr.fmi.pickaname.app.reinit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.common.AbstractMainFragment;
import fr.fmi.pickaname.app.reinit.controller.ReinitController;
import fr.fmi.pickaname.app.reinit.presentation.ReinitView;
import fr.fmi.pickaname.databinding.FragmentReinitBinding;

public class ReinitFragment extends AbstractMainFragment implements ReinitView {

    @Inject ReinitController controller;

    private FragmentReinitBinding binding;

    public static ReinitFragment newInstance() {
        return new ReinitFragment();
    }

    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState
    ) {
        // TODO add BaseFragment
        ReinitComponent.Initializer.init(this).inject(this);

        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setController(controller);
        return binding.getRoot();
    }

    @Override
    public int getTitleId() {
        return R.string.fragment_reinit_title;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_reinit;
    }

    @Override
    public void displayMessage(final String message) {
        binding.setToastMessage(message);
    }
}

package fr.fmi.pickaname.app.reinit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;
import fr.fmi.pickaname.app.reinit.controller.ReinitController;
import fr.fmi.pickaname.app.reinit.presentation.ReinitView;
import fr.fmi.pickaname.databinding.FragmentReinitBinding;

import static fr.fmi.pickaname.app.PickANameApplication.getApplicationModule;

public class ReinitFragment extends AbstractMainFragment implements ReinitView {

    private FragmentReinitBinding binding;
    private ReinitController controller;

    public static ReinitFragment newInstance() {
        return new ReinitFragment();
    }

    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState
    ) {
        final ReinitModule module = new ReinitModule(getApplicationModule(getActivity()), this);
        controller = module.getController();
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

package fr.fmi.pickaname.app.settings;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.common.AbstractMainFragment;
import fr.fmi.pickaname.app.settings.controller.SettingsController;
import fr.fmi.pickaname.app.settings.presentation.SettingsView;
import fr.fmi.pickaname.app.settings.presentation.SettingsScreenViewModel;
import fr.fmi.pickaname.databinding.FragmentSettingsBinding;

public class SettingsFragment extends AbstractMainFragment implements SettingsView {

    @Inject SettingsController controller;

    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState
    ) {
        // TODO add BaseFragment
        SettingsComponent.Initializer.init(this).inject(this);

        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setController(controller);
        load();
        return binding.getRoot();
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public int getTitleId() {
        return R.string.fragment_setting_title;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    public void displayViewModel(final SettingsScreenViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    @Override
    public void displayMessage(final String message) {
        binding.setToastMessage(message);
    }

    private void load() {
        controller.loadSettings();
    }
}

package fr.fmi.pickaname.app.settings;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;
import fr.fmi.pickaname.app.settings.controller.SettingsController;
import fr.fmi.pickaname.app.settings.presentation.SettingsView;
import fr.fmi.pickaname.app.settings.presentation.SettingsScreenViewModel;
import fr.fmi.pickaname.databinding.FragmentSettingsBinding;

import static fr.fmi.pickaname.app.PickANameApplication.getApplicationModule;

public class SettingsFragment extends AbstractMainFragment implements SettingsView {

    private FragmentSettingsBinding binding;
    private SettingsController controller;

    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState
    ) {
        final SettingsModule module = new SettingsModule(getApplicationModule(getActivity()), this);
        controller = module.getController();
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

package fr.fmi.pickaname.app.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;
import fr.fmi.pickaname.app.settings.controller.SettingsController;
import fr.fmi.pickaname.app.settings.presentation.SettingsView;
import fr.fmi.pickaname.app.settings.presentation.SettingsViewModel;
import fr.fmi.pickaname.databinding.FragmentSettingBinding;

import static android.databinding.DataBindingUtil.inflate;
import static fr.fmi.pickaname.app.PickANameApplication.getApplicationModule;

public class SettingsFragment extends AbstractMainFragment implements SettingsView {

    private FragmentSettingBinding binding;
    private SettingsController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final SettingsModule module = new SettingsModule(getApplicationModule(getActivity()), this);
        controller = module.getController();
        binding = inflate(inflater, getLayoutId(), container, false);
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
        return R.layout.fragment_setting;
    }

    @Override
    public void displayViewModel(final SettingsViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    private void load() {
        controller.loadSettings();
    }
}

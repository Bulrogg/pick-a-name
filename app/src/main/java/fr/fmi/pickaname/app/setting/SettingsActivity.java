package fr.fmi.pickaname.app.setting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.databinding.ActivitySettingsBinding;
import fr.fmi.pickaname.app.setting.controller.SettingsController;
import fr.fmi.pickaname.app.setting.presentation.SettingsView;
import fr.fmi.pickaname.app.setting.presentation.SettingsViewModel;

import static fr.fmi.pickaname.app.PickANameApplication.getApplicationModule;

public class SettingsActivity extends AppCompatActivity implements SettingsView {

    private ActivitySettingsBinding binding;
    private SettingsController controller;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SettingsModule module = new SettingsModule(getApplicationModule(this), this);
        controller = module.getController();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        binding.setController(controller);
        load();
    }

    @Override
    protected void onDestroy() {
        binding.unbind();
        super.onDestroy();
    }

    @Override
    public void displayViewModel(final SettingsViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    private void load() {
        controller.loadSettings();
    }
}

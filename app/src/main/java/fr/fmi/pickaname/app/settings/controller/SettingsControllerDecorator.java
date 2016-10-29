package fr.fmi.pickaname.app.settings.controller;

import java.util.concurrent.Executor;

import fr.fmi.pickaname.app.settings.presentation.SettingsScreenViewModel;

public class SettingsControllerDecorator implements SettingsController {

    private final SettingsController controller;

    private final Executor executor;

    public SettingsControllerDecorator(
            final SettingsController controller,
            final Executor executor
    ) {
        this.controller = controller;
        this.executor = executor;
    }

    @Override
    public void loadSettings() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                controller.loadSettings();
            }
        });
    }

    @Override
    public void saveSettings(final SettingsScreenViewModel viewModel) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                controller.saveSettings(viewModel);
            }
        });
    }
}

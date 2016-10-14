package fr.fmi.pickaname.app.setting.controller;

import java.util.concurrent.Executor;

public class SettingsControllerDecorator implements SettingsController {

    private final SettingsController controller;

    private final Executor executor;

    public SettingsControllerDecorator(final SettingsController controller, final Executor executor) {
        this.controller = controller;
        this.executor = executor;
    }

    @Override public void loadSettings() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                controller.loadSettings();
            }
        });
    }
}

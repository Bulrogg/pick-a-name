package fr.fmi.pickaname.app.settings.presentation;

import android.content.Context;

import fr.fmi.pickaname.core.settings.SettingsPresenter;

public class SettingsPresenterImpl implements SettingsPresenter {

    private final SettingsView view;
    private final Context context;

    public SettingsPresenterImpl(final SettingsView view, final Context context) {
        this.view = view;
        this.context = context;
    }

}

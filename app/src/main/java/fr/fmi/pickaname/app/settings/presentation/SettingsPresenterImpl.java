package fr.fmi.pickaname.app.settings.presentation;

import android.content.Context;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.settings.SettingsFragment;
import fr.fmi.pickaname.core.entities.Settings;
import fr.fmi.pickaname.core.settings.SettingsPresenter;

public class SettingsPresenterImpl implements SettingsPresenter {

    private final SettingsView view;
    private final Context context;

    public SettingsPresenterImpl(final SettingsView view, final Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void presentLoading() {
        final SettingsScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = SettingsFragment.VF_LOADING;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentSettings(final Settings settings) {
        final SettingsScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = SettingsFragment.VF_SUCCESS;
        viewModel.lastName = settings.getLastName();
        viewModel.researchType = settings.getResearchType().name();
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentLoadingFailure() {
        final SettingsScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = SettingsFragment.VF_ERROR;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentSaveSettingsSuccess() {
        view.displayMessage(context.getString(R.string.fragment_settings_save_success));
    }

    @Override
    public void presentSaveSettingsFailure() {
        view.displayMessage(context.getString(R.string.fragment_settings_save_failure));
    }

    private SettingsScreenViewModel getViewModel() {
        return new SettingsScreenViewModel();
    }
}

package fr.fmi.pickaname.app.settings.presentation;

import android.content.Context;

import fr.fmi.pickaname.R;
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
        final SettingsViewModel viewModel = getViewModel();
        viewModel.displayedChild = SettingsViewModel.VF_LOADING;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentSettings(Settings settings) {
        final SettingsViewModel viewModel = getViewModel();
        viewModel.displayedChild = SettingsViewModel.VF_SUCCESS;
        viewModel.lastName = settings.getLastName();
        viewModel.researchType = settings.getResearchType().name();
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentLoadingFailure() {
        final SettingsViewModel viewModel = getViewModel();
        viewModel.displayedChild = SettingsViewModel.VF_ERROR;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentSaveSettingsSuccess() {
        view.displayToast(R.string.fragment_settings_save_success);
    }

    @Override
    public void presentSaveSettingsFailure() {
        view.displayToast(R.string.fragment_settings_save_failure);
    }

    private SettingsViewModel getViewModel() {
        return new SettingsViewModel();
    }
}

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
        viewModel.gender = getGenderToDisplay(settings.getResearchType());
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentLoadingFailure() {
        final SettingsViewModel viewModel = getViewModel();
        viewModel.displayedChild = SettingsViewModel.VF_ERROR;
        viewModel.errorResId = R.string.fragment_settings_loading_error;
        view.displayViewModel(viewModel);
    }

    private SettingsViewModel getViewModel() {
        return new SettingsViewModel();
    }

    private String getGenderToDisplay(final Settings.ResearchType type) {
        int resId;
        switch (type) {
            case GIRL:
                resId = R.string.fragment_settings_gender_girl;
                break;
            case BOY:
                resId = R.string.fragment_settings_gender_boy;
                break;
            default:
                resId = R.string.fragment_settings_gender_both;
        }
        return context.getString(resId);
    }
}

package fr.fmi.pickaname.app.sort.presentation;

import android.content.Context;
import android.text.format.DateUtils;

import fr.android.fmi.pickaname.R;
import fr.fmi.pickaname.core.entities.User;
import fr.fmi.pickaname.core.login.LoginPresenter;

import static android.text.format.DateUtils.formatDateTime;

public class SortPresenterImpl implements LoginPresenter {

    private final SortView view;
    private final Context context;

    public SortPresenterImpl(final SortView view, final Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void presentEmptyId() {
        final SortViewModel viewModel = new SortViewModel();
        viewModel.errorResId = R.string.empty_id;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentEmptyPassword() {
        final SortViewModel viewModel = new SortViewModel();
        viewModel.errorResId = R.string.empty_password;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentPendingRequest() {
        final SortViewModel viewModel = new SortViewModel();
        viewModel.displayedChild = SortViewModel.DISPLAY_LOADING;
        viewModel.shouldHideKeyboard = true;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentUnknownId() {
        final SortViewModel viewModel = new SortViewModel();
        viewModel.errorResId = R.string.unknown_id;
        viewModel.displayedChild = SortViewModel.DISPLAY_FORM;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentInvalidPassword() {
        final SortViewModel viewModel = new SortViewModel();
        viewModel.errorResId = R.string.invalid_password;
        viewModel.displayedChild = SortViewModel.DISPLAY_FORM;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentLoggedUser(final User user) {
        final SortViewModel viewModel = new SortViewModel();
        viewModel.displayedChild = SortViewModel.DISPLAY_SUCCESS;
        viewModel.title = getViewModelTitle(user);
        viewModel.description = getViewModelDescription(user);
        view.displayViewModel(viewModel);
    }

    private String getViewModelDescription(final User user) {
        final String lastLoginFormatted = formatDateTime(context, user.getLastLogin().getTime(), DateUtils.FORMAT_ABBREV_WEEKDAY);
        return context.getString(R.string.last_login, lastLoginFormatted);
    }

    private String getViewModelTitle(final User user) {
        String userName = user.getFirstName() + " " + user.getLastName();
        return context.getString(R.string.welcome, userName);
    }
}

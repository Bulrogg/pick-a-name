package fr.fmi.pickaname.app.accepted.presentation;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.fmi.pickaname.app.accepted.FirstNameViewModel;
import fr.fmi.pickaname.core.accepted.AcceptedPresenter;

public class AcceptedPresenterImpl implements AcceptedPresenter {

    private final AcceptedView view;
    private final Context context;

    public AcceptedPresenterImpl(final AcceptedView view, final Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void presentLoading() {
        final AcceptedScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = AcceptedScreenViewModel.VF_LOADING;
        view.displayScreenViewModel(viewModel);
    }

    @Override
    public void presentFirstNames(final List<String> firstNames, final String lastNames) {
        final AcceptedScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = AcceptedScreenViewModel.VF_SUCCESS;
        view.displayScreenViewModel(viewModel);
        view.displayFirstNames(prepareFirstNameViewModels(firstNames, lastNames));
    }

    @Override
    public void presentNoFirstNameAccepted() {
        final AcceptedScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = AcceptedScreenViewModel.VF_NO_FIRST_NAME_ACCEPTED;
        view.displayScreenViewModel(viewModel);
    }

    @Override
    public void presentLoadingFailure() {
        final AcceptedScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = AcceptedScreenViewModel.VF_ERROR;
        view.displayScreenViewModel(viewModel);
    }

    private AcceptedScreenViewModel getViewModel() {
        return new AcceptedScreenViewModel();
    }

    private List<FirstNameViewModel> prepareFirstNameViewModels(
            final List<String> firstNames,
            final String lastNames
    ) {
        final List<FirstNameViewModel> firstNameViewModels = new ArrayList<>();
        for (final String firstName : firstNames) {
            firstNameViewModels.add(new FirstNameViewModel(firstName, lastNames));
        }
        return firstNameViewModels;
    }
}

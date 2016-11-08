package fr.fmi.pickaname.app.rejected.presentation;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import fr.fmi.pickaname.app.accepted.presentation.AcceptedScreenViewModel;
import fr.fmi.pickaname.app.accepted.presentation.AcceptedView;
import fr.fmi.pickaname.app.common.firstname.FirstNameViewModel;
import fr.fmi.pickaname.core.accepted.AcceptedPresenter;
import fr.fmi.pickaname.core.rejected.RejectedPresenter;

public class RejectedPresenterImpl implements RejectedPresenter {

    private final RejectedView view;
    private final Context context;

    public RejectedPresenterImpl(final RejectedView view, final Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void presentLoading() {
        final RejectedScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = RejectedScreenViewModel.VF_LOADING;
        view.displayScreenViewModel(viewModel);
    }

    @Override
    public void presentFirstNames(final List<String> firstNames, final String lastNames) {
        final RejectedScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = RejectedScreenViewModel.VF_SUCCESS;
        view.displayScreenViewModel(viewModel);
        view.displayFirstNames(prepareFirstNameViewModels(firstNames, lastNames));
    }

    @Override
    public void presentNoFirstNameRejected() {
        final RejectedScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = RejectedScreenViewModel.VF_NO_FIRST_NAME_REJECTED;
        view.displayScreenViewModel(viewModel);
    }

    @Override
    public void presentLoadingFailure() {
        final RejectedScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = RejectedScreenViewModel.VF_ERROR;
        view.displayScreenViewModel(viewModel);
    }

    private RejectedScreenViewModel getViewModel() {
        return new RejectedScreenViewModel();
    }

    // TODO mutualiser ce code
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

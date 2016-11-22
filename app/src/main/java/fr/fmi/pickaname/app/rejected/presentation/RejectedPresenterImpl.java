package fr.fmi.pickaname.app.rejected.presentation;

import java.util.ArrayList;
import java.util.List;

import fr.fmi.pickaname.app.common.firstname.FirstNameViewModel;
import fr.fmi.pickaname.app.rejected.RejectedFragment;
import fr.fmi.pickaname.core.rejected.RejectedPresenter;

public class RejectedPresenterImpl implements RejectedPresenter {

    private final RejectedView view;

    public RejectedPresenterImpl(final RejectedView view) {
        this.view = view;
    }

    @Override
    public void presentLoading() {
        final RejectedScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = RejectedFragment.VF_LOADING;
        view.displayScreenViewModel(viewModel);
    }

    @Override
    public void presentFirstNames(final List<String> firstNames, final String lastNames) {
        final RejectedScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = RejectedFragment.VF_SUCCESS;
        view.displayScreenViewModel(viewModel);
        view.displayFirstNames(prepareFirstNameViewModels(firstNames, lastNames));
    }

    @Override
    public void presentNoFirstNameRejected() {
        final RejectedScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = RejectedFragment.VF_NO_FIRST_NAME_REJECTED;
        view.displayScreenViewModel(viewModel);
    }

    @Override
    public void presentLoadingFailure() {
        final RejectedScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = RejectedFragment.VF_ERROR;
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

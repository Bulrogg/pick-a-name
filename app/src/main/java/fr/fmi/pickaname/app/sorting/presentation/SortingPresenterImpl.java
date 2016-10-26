package fr.fmi.pickaname.app.sorting.presentation;

import android.content.Context;

import fr.fmi.pickaname.core.entities.FirstName;
import fr.fmi.pickaname.core.sort.SortingPresenter;

// TODO tester
public class SortingPresenterImpl implements SortingPresenter {

    private final SortingView view;
    private final Context context;

    public SortingPresenterImpl(final SortingView view, final Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void presentSortingScreen(final String lastName) {
        final SortingViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortingViewModel.VF_SUCCESS;
        viewModel.lastName = lastName;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentProposedFirstName(final FirstName firstName) {
        view.displayFirstName(firstName.getFirstName());
    }

    @Override
    public void presentTechnicalError() {
        final SortingViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortingViewModel.VF_ERROR;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentLoading() {
        final SortingViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortingViewModel.VF_LOADING;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentNoMoreFirstName() {
        final SortingViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortingViewModel.VF_NO_MORE_FIRST_NAME;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentAcceptSavingError() {
        // TODO ooo
    }

    @Override
    public void presentRefuseSavingError() {
        // TODO ooo
    }

    private SortingViewModel getViewModel() {
        return new SortingViewModel();
    }

}

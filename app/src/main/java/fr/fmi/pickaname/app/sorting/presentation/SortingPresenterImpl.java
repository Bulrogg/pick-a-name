package fr.fmi.pickaname.app.sorting.presentation;

import android.content.Context;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.sorting.SortingFragment;
import fr.fmi.pickaname.core.entities.FirstName;
import fr.fmi.pickaname.core.sort.SortingPresenter;

public class SortingPresenterImpl implements SortingPresenter {

    private final SortingView view;
    private final Context context;

    public SortingPresenterImpl(final SortingView view, final Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void presentSortingScreen(final String lastName) {
        final SortingScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortingFragment.VF_SUCCESS;
        viewModel.lastName = lastName;
        view.displayScreenViewModel(viewModel);
    }

    @Override
    public void presentProposedFirstName(final FirstName firstName) {
        view.displayFirstName(firstName.getFirstName());
    }

    @Override
    public void presentTechnicalError() {
        final SortingScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortingFragment.VF_ERROR;
        view.displayScreenViewModel(viewModel);
    }

    @Override
    public void presentLoading() {
        final SortingScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortingFragment.VF_LOADING;
        view.displayScreenViewModel(viewModel);
    }

    @Override
    public void presentNoMoreFirstName() {
        final SortingScreenViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortingFragment.VF_NO_MORE_FIRST_NAME;
        view.displayScreenViewModel(viewModel);
    }

    @Override
    public void presentAcceptSavingError() {
        view.displayMessage(context.getString(R.string.fragment_sort_accept_error));
    }

    @Override
    public void presentRefuseSavingError() {
        view.displayMessage(context.getString(R.string.fragment_sort_refuse_error));
    }

    private SortingScreenViewModel getViewModel() {
        return new SortingScreenViewModel();
    }

}

package fr.fmi.pickaname.app.sorting.presentation;

import android.content.Context;

import fr.fmi.pickaname.core.entities.FirstName;
import fr.fmi.pickaname.core.sort.SortPresenter;

public class SortingPresenterImpl implements SortPresenter {

    private final SortingView view;
    private final Context context;

    public SortingPresenterImpl(final SortingView view, final Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void presentAFirstName(final FirstName firstName) {
        final SortingViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortingViewModel.VF_SORT_SUCCESS;
        viewModel.firstName = firstName.getFirstName();
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentLoadingFailure() {
        final SortingViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortingViewModel.VF_SORT_ERROR;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentLoading() {
        final SortingViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortingViewModel.VF_SORT_LOADING;
        view.displayViewModel(viewModel);
    }

    private SortingViewModel getViewModel() {
        return new SortingViewModel();
    }

}

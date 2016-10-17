package fr.fmi.pickaname.app.sort.presentation;

import android.content.Context;

import fr.fmi.pickaname.core.entities.FirstName;
import fr.fmi.pickaname.core.sort.SortPresenter;

public class SortPresenterImpl implements SortPresenter {

    private final SortView view;
    private final Context context;

    public SortPresenterImpl(final SortView view, final Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void presentAFirstName(final FirstName firstName) {
        final SortViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortViewModel.VF_SORT_SUCCESS;
        viewModel.firstName = firstName.getFirstName();
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentLoadingFailure() {
        final SortViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortViewModel.VF_SORT_ERROR;
        view.displayViewModel(viewModel);
    }

    @Override
    public void presentLoading() {
        final SortViewModel viewModel = getViewModel();
        viewModel.displayedChild = SortViewModel.VF_SORT_LOADING;
        view.displayViewModel(viewModel);
    }

    private SortViewModel getViewModel() {
        return new SortViewModel();
    }

}

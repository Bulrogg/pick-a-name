package fr.fmi.pickaname.app.sorting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;
import fr.fmi.pickaname.app.sorting.controller.SortingController;
import fr.fmi.pickaname.app.sorting.presentation.SortingView;
import fr.fmi.pickaname.app.sorting.presentation.SortingScreenViewModel;
import fr.fmi.pickaname.databinding.FragmentSortingBinding;

import static fr.fmi.pickaname.app.PickANameApplication.getApplicationModule;

public class SortingFragment extends AbstractMainFragment implements SortingView {

    private FragmentSortingBinding binding;
    private SortingController controller;

    public static SortingFragment newInstance() {
        return new SortingFragment();
    }

    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState
    ) {
        final SortingModule module = new SortingModule(getApplicationModule(getActivity()), this);
        controller = module.getController();
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setController(controller);
        load();
        return binding.getRoot();
    }

    @Override
    public int getTitleId() {
        return R.string.fragment_sort_title;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_sorting;
    }

    @Override
    public void displayScreenViewModel(final SortingScreenViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    @Override
    public void displayFirstName(final String firstName) {
        binding.setFirstName(firstName);
    }

    @Override
    public void displayToast(final String message) {
        binding.setToastMessage(message);
    }

    private void load() {
        controller.load();
    }

}
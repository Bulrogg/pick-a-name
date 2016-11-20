package fr.fmi.pickaname.app.sorting;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.common.AbstractMainFragment;
import fr.fmi.pickaname.app.sorting.controller.SortingController;
import fr.fmi.pickaname.app.sorting.presentation.SortingScreenViewModel;
import fr.fmi.pickaname.app.sorting.presentation.SortingView;
import fr.fmi.pickaname.databinding.FragmentSortingBinding;

public class SortingFragment extends AbstractMainFragment implements SortingView {

    @Inject SortingController controller;

    private FragmentSortingBinding binding;

    public static SortingFragment newInstance() {
        return new SortingFragment();
    }

    @Override
    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState
    ) {
        // TODO add BaseFragment
        SortingComponent.Initializer.init(this).inject(this);

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
    public void displayMessage(final String message) {
        binding.setToastMessage(message);
    }

    private void load() {
        controller.load();
    }

}
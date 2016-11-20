package fr.fmi.pickaname.app.accepted;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.accepted.controller.AcceptedController;
import fr.fmi.pickaname.app.accepted.presentation.AcceptedScreenViewModel;
import fr.fmi.pickaname.app.accepted.presentation.AcceptedView;
import fr.fmi.pickaname.app.common.AbstractMainFragment;
import fr.fmi.pickaname.app.common.firstname.FirstNameAdapter;
import fr.fmi.pickaname.app.common.firstname.FirstNameViewModel;
import fr.fmi.pickaname.databinding.FragmentAcceptedBinding;

public class AcceptedFragment extends AbstractMainFragment implements AcceptedView {

    @Inject AcceptedController controller;

    private FragmentAcceptedBinding binding;

    private final FirstNameAdapter adapter = new FirstNameAdapter();

    public static AcceptedFragment newInstance() {
        return new AcceptedFragment();
    }

    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState
    ) {
        // TODO add BaseFragment
        AcceptedComponent.Initializer.init(this).inject(this);

        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setController(controller);

        initRecyclerView();
        load();
        return binding.getRoot();
    }

    @Override
    public int getTitleId() {
        return R.string.fragment_accepted_title;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_accepted;
    }

    @Override
    public void displayScreenViewModel(final AcceptedScreenViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    @Override
    public void displayFirstNames(final List<FirstNameViewModel> firstNameItems) {
        adapter.addFirstNames(firstNameItems);
    }

    @Override
    public void displayMessage(final String message) {
        binding.setToastMessage(message);
    }

    private void load() {
        controller.load();
    }

    private void initRecyclerView() {
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerAccepted.setHasFixedSize(true);
        binding.recyclerAccepted.setLayoutManager(layoutManager);
        binding.recyclerAccepted.setAdapter(adapter);
    }

}

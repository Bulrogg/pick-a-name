package fr.fmi.pickaname.app.rejected;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;
import fr.fmi.pickaname.app.common.firstname.FirstNameAdapter;
import fr.fmi.pickaname.app.common.firstname.FirstNameViewModel;
import fr.fmi.pickaname.app.rejected.controller.RejectedController;
import fr.fmi.pickaname.app.rejected.presentation.RejectedScreenViewModel;
import fr.fmi.pickaname.app.rejected.presentation.RejectedView;
import fr.fmi.pickaname.databinding.FragmentRejectedBinding;

import static fr.fmi.pickaname.app.PickANameApplication.getApplicationModule;

public class RejectedFragment extends AbstractMainFragment implements RejectedView {

    private FragmentRejectedBinding binding;
    private RejectedController controller;

    private final FirstNameAdapter adapter = new FirstNameAdapter();

    public static RejectedFragment newInstance() {
        return new RejectedFragment();
    }

    public View onCreateView(
            final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState
    ) {
        final RejectedModule module = new RejectedModule(getApplicationModule(getActivity()), this);
        controller = module.getController();
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setController(controller);
        initRecyclerView();
        load();
        return binding.getRoot();
    }

    @Override
    public int getTitleId() {
        return R.string.fragment_rejected_title;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rejected;
    }

    @Override
    public void displayScreenViewModel(RejectedScreenViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    @Override
    public void displayMessage(String message) {
        binding.setToastMessage(message);
    }

    @Override
    public void displayFirstNames(List<FirstNameViewModel> firstNameItems) {
        adapter.addFirstNames(firstNameItems);
    }

    private void load() {
        controller.load();
    }

    private void initRecyclerView() {
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        binding.recyclerRejected.setHasFixedSize(true);
        binding.recyclerRejected.setLayoutManager(layoutManager);
        binding.recyclerRejected.setAdapter(adapter);
    }
}

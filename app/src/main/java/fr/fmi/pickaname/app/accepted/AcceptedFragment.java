package fr.fmi.pickaname.app.accepted;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.accepted.controller.AcceptedController;
import fr.fmi.pickaname.app.accepted.presentation.AcceptedScreenViewModel;
import fr.fmi.pickaname.app.accepted.presentation.AcceptedView;
import fr.fmi.pickaname.app.common.AbstractMainFragment;
import fr.fmi.pickaname.app.common.firstname.FirstNameAdapter;
import fr.fmi.pickaname.app.common.firstname.FirstNameViewModel;

public class AcceptedFragment extends AbstractMainFragment implements AcceptedView {

    @BindView(R.id.view_flipper) ViewFlipper viewFlipper;
    @BindView(R.id.recycler_accepted) RecyclerView recyclerAcceptedView;

    @Inject AcceptedController controller;

    private final FirstNameAdapter adapter = new FirstNameAdapter();

    public static AcceptedFragment newInstance() {
        return new AcceptedFragment();
    }

    @Override
    public int getTitleId() {
        return R.string.fragment_accepted_title;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_accepted;
    }

    @Override
    protected void injectDependencies() {
        AcceptedComponent.Initializer.init(this).inject(this);
    }

    @Override
    public void init() {
        initRecyclerView();
        load();
    }

    @Override
    public void displayScreenViewModel(final AcceptedScreenViewModel viewModel) {
        viewFlipper.setDisplayedChild(viewModel.displayedChild);
    }

    @Override
    public void displayFirstNames(final List<FirstNameViewModel> firstNameItems) {
        adapter.addFirstNames(firstNameItems);
    }

    @Override
    public void displayMessage(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    private void load() {
        controller.load();
    }

    private void initRecyclerView() {
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerAcceptedView.setHasFixedSize(true);
        recyclerAcceptedView.setLayoutManager(layoutManager);
        recyclerAcceptedView.setAdapter(adapter);
    }
}

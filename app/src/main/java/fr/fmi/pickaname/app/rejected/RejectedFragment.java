package fr.fmi.pickaname.app.rejected;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.common.AbstractMainFragment;
import fr.fmi.pickaname.app.common.firstname.FirstNameAdapter;
import fr.fmi.pickaname.app.common.firstname.FirstNameViewModel;
import fr.fmi.pickaname.app.rejected.controller.RejectedController;
import fr.fmi.pickaname.app.rejected.presentation.RejectedScreenViewModel;
import fr.fmi.pickaname.app.rejected.presentation.RejectedView;

public class RejectedFragment extends AbstractMainFragment implements RejectedView {

    public static final int VF_LOADING = 0;
    public static final int VF_SUCCESS = 1;
    public static final int VF_ERROR = 2;
    public static final int VF_NO_FIRST_NAME_REJECTED = 3;

    @BindView(R.id.view_flipper) ViewFlipper viewFlipper;
    @BindView(R.id.recycler_rejected) RecyclerView recyclerRejectedView;

    @Inject RejectedController controller;
    @Inject RejectedViewDecorator viewDecorator;

    private final FirstNameAdapter adapter = new FirstNameAdapter();

    public static RejectedFragment newInstance() {
        return new RejectedFragment();
    }

    @Override
    public int getTitleId() {
        return R.string.fragment_rejected_title;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_rejected;
    }

    @Override
    protected void injectDependencies() {
        RejectedComponent.Initializer.init(this).inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        viewDecorator.setRejectedView(this);
        initRecyclerView();
        load();
    }

    @Override
    public void onStop() {
        viewDecorator.setRejectedView(null);
        super.onStop();
    }

    @Override
    public void displayScreenViewModel(final RejectedScreenViewModel viewModel) {
        viewFlipper.setDisplayedChild(viewModel.displayedChild);
    }

    @Override
    public void displayMessage(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
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
        recyclerRejectedView.setHasFixedSize(true);
        recyclerRejectedView.setLayoutManager(layoutManager);
        recyclerRejectedView.setAdapter(adapter);
    }
}

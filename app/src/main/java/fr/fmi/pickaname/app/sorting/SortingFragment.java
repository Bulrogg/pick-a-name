package fr.fmi.pickaname.app.sorting;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.common.AbstractMainFragment;
import fr.fmi.pickaname.app.sorting.controller.SortingController;
import fr.fmi.pickaname.app.sorting.presentation.SortingScreenViewModel;
import fr.fmi.pickaname.app.sorting.presentation.SortingView;

public class SortingFragment extends AbstractMainFragment implements SortingView {

    public static final int VF_LOADING = 0;
    public static final int VF_SUCCESS = 1;
    public static final int VF_ERROR = 2;
    public static final int VF_NO_MORE_FIRST_NAME = 3;

    @BindView(R.id.view_flipper) ViewFlipper viewFlipper;
    @BindView(R.id.first_name_view) TextView firstNameView;
    @BindView(R.id.last_name_view) TextView lastNameView;

    @Inject SortingController controller;
    @Inject SortingViewDecorator viewDecorator;

    public static SortingFragment newInstance() {
        return new SortingFragment();
    }

    @Override
    public int getTitleId() {
        return R.string.fragment_sort_title;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_sorting;
    }

    @Override
    protected void injectDependencies() {
        SortingComponent.Initializer.init(this).inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        viewDecorator.setSortingView(this);
        load();
    }

    @Override
    public void onStop() {
        viewDecorator.setSortingView(null);
        super.onStop();
    }

    @Override
    public void displayScreenViewModel(final SortingScreenViewModel viewModel) {
        viewFlipper.setDisplayedChild(viewModel.displayedChild);
        lastNameView.setText(viewModel.lastName);
    }

    @Override
    public void displayFirstName(final String firstName) {
        firstNameView.setText(firstName);
    }

    @Override
    public void displayMessage(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.accept_button)
    public void acceptClickHandler() {
        controller.accept(firstNameView.getText().toString());
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.refuse_button)
    public void refuseClickHandler() {
        controller.refuse(firstNameView.getText().toString());
    }

    private void load() {
        controller.load();
    }

}
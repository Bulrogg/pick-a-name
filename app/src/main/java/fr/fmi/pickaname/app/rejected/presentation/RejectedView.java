package fr.fmi.pickaname.app.rejected.presentation;

import java.util.List;

import fr.fmi.pickaname.app.common.firstname.FirstNameViewModel;

public interface RejectedView {

    void displayScreenViewModel(RejectedScreenViewModel viewModel);

    void displayToast(String message);

    void displayFirstNames(List<FirstNameViewModel> firstNameItems);
}

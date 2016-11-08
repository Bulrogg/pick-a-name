package fr.fmi.pickaname.app.accepted.presentation;

import java.util.List;

import fr.fmi.pickaname.app.accepted.FirstNameViewModel;

public interface AcceptedView {

    void displayScreenViewModel(AcceptedScreenViewModel viewModel);

    void displayToast(String message);

    void displayFirstNames(List<FirstNameViewModel> firstNameItems);
}

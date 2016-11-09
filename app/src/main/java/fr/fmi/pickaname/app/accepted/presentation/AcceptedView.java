package fr.fmi.pickaname.app.accepted.presentation;

import java.util.List;

import fr.fmi.pickaname.app.common.firstname.FirstNameViewModel;

public interface AcceptedView {

    void displayScreenViewModel(AcceptedScreenViewModel viewModel);

    void displayMessage(String message);

    void displayFirstNames(List<FirstNameViewModel> firstNameItems);
}

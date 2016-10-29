package fr.fmi.pickaname.app.sorting.presentation;

public interface SortingView {

    void displayScreenViewModel(SortingScreenViewModel viewModel);

    void displayFirstName(String firstName);

    void displayToast(String message);

}

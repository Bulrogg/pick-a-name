package fr.fmi.pickaname.app.sorting.controller;

public interface SortingController {

    void load();

    void accept(String firstName);

    void refuse(String firstName);

}

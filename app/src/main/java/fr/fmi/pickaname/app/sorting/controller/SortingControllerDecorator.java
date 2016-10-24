package fr.fmi.pickaname.app.sorting.controller;

import java.util.concurrent.Executor;

public class SortingControllerDecorator implements SortingController {

    private final SortingController controller;

    private final Executor executor;

    public SortingControllerDecorator(final SortingController controller, final Executor executor) {
        this.controller = controller;
        this.executor = executor;
    }

    @Override
    public void load() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                controller.load();
            }
        });
    }

    @Override
    public void accept(final String firstName) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                controller.accept(firstName);
            }
        });
    }

    @Override
    public void refuse(final String firstName) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                controller.refuse(firstName);
            }
        });
    }
}
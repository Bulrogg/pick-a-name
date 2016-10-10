package fr.fmi.pickaname.app.sort.controller;

import java.util.concurrent.Executor;

public class SortControllerDecorator implements SortController {

    private final SortController controller;

    private final Executor executor;

    public SortControllerDecorator(final SortController controller, final Executor executor) {
        this.controller = controller;
        this.executor = executor;
    }

    @Override
    public void loadFirstNames() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                controller.loadFirstNames();
            }
        });
    }

}

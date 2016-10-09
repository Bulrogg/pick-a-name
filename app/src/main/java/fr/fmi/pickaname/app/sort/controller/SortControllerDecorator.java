package fr.fmi.pickaname.app.sort.controller;

import java.util.concurrent.Executor;

public class SortControllerDecorator implements SortController {

    private final SortController controller;

    private final Executor executor;

    public SortControllerDecorator(SortController controller, Executor executor) {
        this.controller = controller;
        this.executor = executor;
    }

    @Override
    public void executeRequest(final String name, final String password) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                controller.executeRequest(name, password);
            }
        });
    }
}

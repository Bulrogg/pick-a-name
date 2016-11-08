package fr.fmi.pickaname.app.rejected.controller;

import java.util.concurrent.Executor;

public class RejectedControllerDecorator implements RejectedController {

    private final RejectedController controller;

    private final Executor executor;

    public RejectedControllerDecorator(
            final RejectedController controller,
            final Executor executor
    ) {
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
}

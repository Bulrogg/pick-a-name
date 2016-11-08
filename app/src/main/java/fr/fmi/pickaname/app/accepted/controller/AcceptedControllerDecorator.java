package fr.fmi.pickaname.app.accepted.controller;

import java.util.concurrent.Executor;

public class AcceptedControllerDecorator implements AcceptedController {

    private final AcceptedController controller;

    private final Executor executor;

    public AcceptedControllerDecorator(
            final AcceptedController controller,
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

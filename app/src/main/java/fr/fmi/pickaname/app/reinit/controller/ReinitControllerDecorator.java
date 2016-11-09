package fr.fmi.pickaname.app.reinit.controller;

import java.util.concurrent.Executor;

public class ReinitControllerDecorator implements ReinitController {

    private final ReinitController controller;

    private final Executor executor;

    public ReinitControllerDecorator(
            final ReinitController controller,
            final Executor executor
    ) {
        this.controller = controller;
        this.executor = executor;
    }

    @Override
    public void reinitialize() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                controller.reinitialize();
            }
        });
    }
}

package fr.fmi.pickaname.app.reinit;

import dagger.Component;
import fr.fmi.pickaname.app.ApplicationComponent;
import fr.fmi.pickaname.app.common.SingleIn;

@SingleIn(ReinitComponent.class)
@Component(
        modules = ReinitModule.class,
        dependencies = ApplicationComponent.class
)
interface ReinitComponent {

    final class Initializer {

        private Initializer() {
            // No instances
        }

        static ReinitComponent init(final ReinitFragment fragment) {
            // TODO ooo
            /*return DaggerHomeComponent
                    .builder()
                    .applicationComponent(CosmoApplication.get(activity).getComponent())
                    .homeModule(new HomeModule())
                    .build();*/
            return null;
        }
    }

    void inject(final ReinitFragment fragment);
}
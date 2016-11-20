package fr.fmi.pickaname.app.rejected;

import dagger.Component;
import fr.fmi.pickaname.app.ApplicationComponent;
import fr.fmi.pickaname.app.common.SingleIn;

@SingleIn(RejectedComponent.class)
@Component(
        modules = RejectedModule.class,
        dependencies = ApplicationComponent.class
)
interface RejectedComponent {

    final class Initializer {

        private Initializer() {
            // No instances
        }

        static RejectedComponent init(final RejectedFragment fragment) {
            // TODO ooo
            /*return DaggerHomeComponent
                    .builder()
                    .applicationComponent(CosmoApplication.get(activity).getComponent())
                    .homeModule(new HomeModule())
                    .build();*/
            return null;
        }
    }

    void inject(final RejectedFragment fragment);
}
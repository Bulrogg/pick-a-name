package fr.fmi.pickaname.app.sorting;

import dagger.Component;
import fr.fmi.pickaname.app.ApplicationComponent;
import fr.fmi.pickaname.app.common.SingleIn;

@SingleIn(SortingComponent.class)
@Component(
        modules = SortingModule.class,
        dependencies = ApplicationComponent.class
)
interface SortingComponent {

    final class Initializer {

        private Initializer() {
            // No instances
        }

        static SortingComponent init(final SortingFragment fragment) {
            // TODO ooo
            /*return DaggerHomeComponent
                    .builder()
                    .applicationComponent(CosmoApplication.get(activity).getComponent())
                    .homeModule(new HomeModule())
                    .build();*/
            return null;
        }
    }

    void inject(final SortingFragment fragment);
}
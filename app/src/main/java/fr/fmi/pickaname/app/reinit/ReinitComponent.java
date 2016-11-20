package fr.fmi.pickaname.app.reinit;

import dagger.Component;
import fr.fmi.pickaname.app.ApplicationComponent;
import fr.fmi.pickaname.app.PickANameApplication;
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
            return DaggerReinitComponent
                    .builder()
                    .applicationComponent(PickANameApplication.get(fragment.getActivity())
                                                              .getComponent())
                    .reinitModule(new ReinitModule(fragment))
                    .build();
        }
    }

    void inject(final ReinitFragment fragment);
}
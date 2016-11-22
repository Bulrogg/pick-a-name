package fr.fmi.pickaname.app.accepted;

import dagger.Component;
import fr.fmi.pickaname.app.ApplicationComponent;
import fr.fmi.pickaname.app.PickANameApplication;
import fr.fmi.pickaname.app.common.SingleIn;

@SingleIn(AcceptedComponent.class)
@Component(
        modules = AcceptedModule.class,
        dependencies = ApplicationComponent.class
)
interface AcceptedComponent {

    final class Initializer {

        private Initializer() {
            // No instances
        }

        static AcceptedComponent init(final AcceptedFragment fragment) {
            return DaggerAcceptedComponent
                    .builder()
                    .applicationComponent(PickANameApplication.get(fragment.getActivity())
                                                              .getComponent())
                    .acceptedModule(new AcceptedModule())
                    .build();
        }
    }

    void inject(final AcceptedFragment fragment);
}
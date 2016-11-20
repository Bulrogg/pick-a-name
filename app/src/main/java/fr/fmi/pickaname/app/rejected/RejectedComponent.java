package fr.fmi.pickaname.app.rejected;

import dagger.Component;
import fr.fmi.pickaname.app.ApplicationComponent;
import fr.fmi.pickaname.app.PickANameApplication;
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
            return DaggerRejectedComponent
                    .builder()
                    .applicationComponent(PickANameApplication.get(fragment.getActivity())
                                                              .getComponent())
                    .rejectedModule(new RejectedModule(fragment))
                    .build();
        }
    }

    void inject(final RejectedFragment fragment);
}
package fr.fmi.pickaname.app.settings;

import dagger.Component;
import fr.fmi.pickaname.app.ApplicationComponent;
import fr.fmi.pickaname.app.PickANameApplication;
import fr.fmi.pickaname.app.common.SingleIn;

@SingleIn(SettingsComponent.class)
@Component(
        modules = SettingsModule.class,
        dependencies = ApplicationComponent.class
)
interface SettingsComponent {

    final class Initializer {

        private Initializer() {
            // No instances
        }

        static SettingsComponent init(final SettingsFragment fragment) {
            return DaggerSettingsComponent
                    .builder()
                    .applicationComponent(PickANameApplication.get(fragment.getActivity())
                                                              .getComponent())
                    .settingsModule(new SettingsModule(fragment))
                    .build();
        }
    }

    void inject(final SettingsFragment fragment);
}
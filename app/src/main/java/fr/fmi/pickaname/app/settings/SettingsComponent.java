package fr.fmi.pickaname.app.settings;

import dagger.Component;
import fr.fmi.pickaname.app.ApplicationComponent;
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
            // TODO ooo
            /*return DaggerHomeComponent
                    .builder()
                    .applicationComponent(CosmoApplication.get(activity).getComponent())
                    .homeModule(new HomeModule())
                    .build();*/
            return null;
        }
    }

    void inject(final SettingsFragment fragment);
}
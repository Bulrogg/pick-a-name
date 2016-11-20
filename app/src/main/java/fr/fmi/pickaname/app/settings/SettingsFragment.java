package fr.fmi.pickaname.app.settings;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.common.AbstractMainFragment;
import fr.fmi.pickaname.app.settings.controller.SettingsController;
import fr.fmi.pickaname.app.settings.presentation.SettingsScreenViewModel;
import fr.fmi.pickaname.app.settings.presentation.SettingsView;

public class SettingsFragment extends AbstractMainFragment implements SettingsView {

    @BindView(R.id.view_flipper) ViewFlipper viewFlipper;
    @BindView(R.id.last_name_view) TextView lastNameView;
    @BindView(R.id.spinner_research_type) Spinner researchTypeSpinner;

    @Inject SettingsController controller;

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public int getTitleId() {
        return R.string.fragment_setting_title;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void injectDependencies() {
        SettingsComponent.Initializer.init(this).inject(this);
    }

    @Override
    public void init() {
        load();
    }

    @Override
    public void displayViewModel(final SettingsScreenViewModel viewModel) {
        viewFlipper.setDisplayedChild(viewModel.displayedChild);
        lastNameView.setText(viewModel.lastName);
        researchTypeSpinner.setSelection(getResearchTypePositionIndex(viewModel.researchType));
    }

    @Override
    public void displayMessage(final String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unused")
    @OnClick(R.id.save_button)
    public void saveClickHandler() {
        controller.saveSettings(
                lastNameView.getText().toString(),
                (String) researchTypeSpinner.getSelectedItem());
    }

    private void load() {
        controller.loadSettings();
    }


    private int getResearchTypePositionIndex(final String researchType) {
        for (int pos = 0; pos < researchTypeSpinner.getCount(); pos++) {
            final String value = researchTypeSpinner.getItemAtPosition(pos).toString();
            if (value.equalsIgnoreCase(researchType)) {
                return pos;
            }
        }
        return 0;
    }
}

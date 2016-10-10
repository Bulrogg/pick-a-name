package fr.fmi.pickaname.app.sort;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import fr.android.fmi.pickaname.R;
import fr.android.fmi.pickaname.databinding.ActivitySortBinding;
import fr.fmi.pickaname.app.sort.controller.SortController;
import fr.fmi.pickaname.app.sort.presentation.SortView;
import fr.fmi.pickaname.app.sort.presentation.SortViewModel;

import static fr.fmi.pickaname.app.PickANameApplication.getApplicationModule;

public class SortActivity extends AppCompatActivity implements SortView {

    private ActivitySortBinding binding;
    private SortController controller;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final SortModule module = new SortModule(getApplicationModule(this), this);
        controller = module.getController();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sort);
        binding.setController(controller);
        load();
    }

    @Override
    protected void onDestroy() {
        binding.unbind();
        super.onDestroy();
    }

    @Override
    public void displayViewModel(SortViewModel viewModel) {
        binding.setViewModel(viewModel);
    }

    private void load() {
        controller.loadFirstNames();
    }
}

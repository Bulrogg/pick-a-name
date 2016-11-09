package fr.fmi.pickaname.app.main;

import android.app.FragmentManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;
import fr.fmi.pickaname.app.about.AboutFragment;
import fr.fmi.pickaname.app.accepted.AcceptedFragment;
import fr.fmi.pickaname.app.feedback.FeedbackFragment;
import fr.fmi.pickaname.app.rejected.RejectedFragment;
import fr.fmi.pickaname.app.settings.SettingsFragment;
import fr.fmi.pickaname.app.sorting.SortingFragment;
import fr.fmi.pickaname.databinding.ActivityMainBinding;

// TODO tester le cas ou l'activité est détruite avant la réponse de l'interactor
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initToggle();
        binding.navigation.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        final int navId = item.getItemId();
        switch (navId) {
            case R.id.nav_sorting:
                loadMainFragment(SortingFragment.newInstance());
                break;
            case R.id.nav_accepted:
                loadMainFragment(AcceptedFragment.newInstance());
                break;
            case R.id.nav_refused:
                loadMainFragment(RejectedFragment.newInstance());
                break;
            case R.id.nav_configuration:
                loadMainFragment(SettingsFragment.newInstance());
                break;
            case R.id.nav_about:
                loadMainFragment(AboutFragment.newInstance());
                break;
            case R.id.nav_feedback:
                loadMainFragment(FeedbackFragment.newInstance());
                break;
        }
        binding.drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadMainFragment(final AbstractMainFragment mainFragment) {
        final FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, mainFragment).commit();
        setTitle(mainFragment.getTitleId());
    }

    private void initToggle() {
        setSupportActionBar(binding.toolbar);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                                                                       binding.drawer,
                                                                       binding.toolbar,
                                                                       R.string.nav_drawer_open,
                                                                       R.string.nav_drawer_close);
        binding.drawer.setDrawerListener(toggle);
        toggle.syncState();
    }
}

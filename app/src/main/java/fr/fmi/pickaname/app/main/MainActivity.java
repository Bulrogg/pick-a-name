package fr.fmi.pickaname.app.main;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.PickANameApplication;
import fr.fmi.pickaname.app.accepted.AcceptedFragment;
import fr.fmi.pickaname.app.common.AbstractBaseActivity;
import fr.fmi.pickaname.app.common.AbstractMainFragment;
import fr.fmi.pickaname.app.feedback.FeedbackFragment;
import fr.fmi.pickaname.app.reinit.ReinitFragment;
import fr.fmi.pickaname.app.rejected.RejectedFragment;
import fr.fmi.pickaname.app.settings.SettingsFragment;
import fr.fmi.pickaname.app.sorting.SortingFragment;

public class MainActivity extends AbstractBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.navigation) NavigationView navigationView;
    @BindView(R.id.drawer) DrawerLayout drawer;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToggle();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDependencies() {
        PickANameApplication.get(this).getComponent().inject(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
            case R.id.nav_reinit:
                loadMainFragment(ReinitFragment.newInstance());
                break;
            case R.id.nav_feedback:
                loadMainFragment(FeedbackFragment.newInstance());
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadMainFragment(final AbstractMainFragment mainFragment) {
        final FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, mainFragment).commit();
        setTitle(mainFragment.getTitleId());
    }

    private void initToggle() {
        setSupportActionBar(toolbar);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                                                                       drawer,
                                                                       toolbar,
                                                                       R.string.nav_drawer_open,
                                                                       R.string.nav_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }
}

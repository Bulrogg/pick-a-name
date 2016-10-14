package fr.fmi.pickaname.app.main;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.fmi.pickaname.R;
import fr.fmi.pickaname.app.AbstractMainFragment;
import fr.fmi.pickaname.app.about.AboutFragment;
import fr.fmi.pickaname.app.accepted.AcceptedFragment;
import fr.fmi.pickaname.app.feedback.FeedbackFragment;
import fr.fmi.pickaname.app.rejected.RejectedFragment;
import fr.fmi.pickaname.app.setting.SettingFragment;
import fr.fmi.pickaname.app.sort.SortFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToggle();
        navigationView.setNavigationItemSelectedListener(this);
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
                loadMainFragment(SortFragment.newInstance());
                break;
            case R.id.nav_accepted:
                loadMainFragment(AcceptedFragment.newInstance());
                break;
            case R.id.nav_refused:
                loadMainFragment(RejectedFragment.newInstance());
                break;
            case R.id.nav_configuration:
                loadMainFragment(SettingFragment.newInstance());
                break;
            case R.id.nav_about:
                loadMainFragment(AboutFragment.newInstance());
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
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }
}

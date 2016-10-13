package fr.fmi.pickaname;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

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
                Toast.makeText(this, "TODO sorting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_accepted:
                Toast.makeText(this, "TODO accepted", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_refused:
                Toast.makeText(this, "TODO refused", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_configuration:
                Toast.makeText(this, "TODO configuration", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_reinitialisation:
                Toast.makeText(this, "TODO reinitialisation", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_about:
                Toast.makeText(this, "TODO about", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_stars:
                Toast.makeText(this, "TODO stars", Toast.LENGTH_SHORT).show();
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

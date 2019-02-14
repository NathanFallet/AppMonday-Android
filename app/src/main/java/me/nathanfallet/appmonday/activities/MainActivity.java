package me.nathanfallet.appmonday.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import me.nathanfallet.appmonday.R;
import me.nathanfallet.appmonday.fragments.CompetitionsFragment;
import me.nathanfallet.appmonday.fragments.ProjectsFragment;
import me.nathanfallet.appmonday.fragments.SettingsFragment;
import me.nathanfallet.appmonday.fragments.TipsFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private ProjectsFragment projectsFragment;
    private CompetitionsFragment competitionsFragment;
    private TipsFragment tipsFragment;
    private SettingsFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainNav = findViewById(R.id.main_nav);
        mMainFrame = findViewById(R.id.main_frame);

        projectsFragment = new ProjectsFragment();
        competitionsFragment = new CompetitionsFragment();
        tipsFragment = new TipsFragment();
        settingsFragment = new SettingsFragment();

        setFragment("Projects", projectsFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_projects:
                        setFragment("Projects", projectsFragment);
                        break;
                    case R.id.nav_competitions:
                        setFragment("Competitions", competitionsFragment);
                        break;
                    case R.id.nav_tips:
                        setFragment("Tips", tipsFragment);
                        break;
                    case R.id.nav_settings:
                        setFragment("Settings", settingsFragment);
                        break;
                }

                return true;
            }
        });
    }

    private void setFragment(String title, Fragment fragment) {
        setTitle(title);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

}

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

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private ProjectsFragment projectsFragment;
    private CompetitionsFragment competitionsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainNav = findViewById(R.id.main_nav);
        mMainFrame = findViewById(R.id.main_frame);

        projectsFragment = new ProjectsFragment();
        competitionsFragment = new CompetitionsFragment();

        setTitle("Projects");
        setFragment(projectsFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_projects:
                        setTitle("Projects");
                        setFragment(projectsFragment);
                        break;
                    case R.id.nav_competitions:
                        setTitle("Competitions");
                        setFragment(competitionsFragment);
                        break;
                    case R.id.nav_tips:

                        break;
                    case R.id.nav_settings:

                        break;
                }

                return true;
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

}

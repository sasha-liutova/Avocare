package com.liutova.avocare.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.liutova.avocare.R;
import com.liutova.avocare.view.fragment.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Oleksandra Liutova on 12-Mar-16.
 */
public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.side_menu)
    NavigationView mNavigationView;

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {

            /**
             * Called when a drawer has settled in a completely closed state.
             */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                //getActionBar().setTitle(mTitle);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /**
             * Called when a drawer has settled in a completely open state.
             */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //getActionBar().setTitle(mDrawerTitle);
                //invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };


        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    public void replaceFragment(BaseFragment baseFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, baseFragment, baseFragment.getTag()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        switch (menuItem.getItemId()){
            case R.id.navigation_item_home:
                Log.d("navigation_item_home", "navigation_item_home: ");
                return true;
            case R.id.navigation_item_history:
                Log.d("navigation_item_history", "navigation_item_history: ");
                return true;
            case R.id.navigation_item_favourites:
                Log.d("navigation_item_favourites", "navigation_item_favourites: ");
                return true;
            case R.id.navigation_item_my_alergens:
                Log.d("navigation_item_my_alergens", "navigation_item_my_alergens: ");
                return true;
            case R.id.navigation_item_settings:
                Log.d("navigation_item_settings", "navigation_item_settings: ");
                return true;
            case R.id.navigation_item_help:
                Log.d("navigation_item_help", "navigation_item_help: ");
                return true;
            case R.id.navigation_item_share:
                Log.d("navigation_item_share", "navigation_item_share: ");
                return true;
            case R.id.navigation_item_about:
                Log.d("navigation_item_about", "navigation_item_about: ");
                return true;
        }
        return false;
    }
}

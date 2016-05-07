package com.liutova.avocare.view.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.liutova.avocare.R;
import com.liutova.avocare.helper.Helper;
import com.liutova.avocare.view.fragment.AboutFragment;
import com.liutova.avocare.view.fragment.AlergensFragment;
import com.liutova.avocare.view.fragment.BaseFragment;
import com.liutova.avocare.view.fragment.FavouritesFragment;
import com.liutova.avocare.view.fragment.HelpFragment;
import com.liutova.avocare.view.fragment.HistoryFragment;
import com.liutova.avocare.view.fragment.MainFragment;
import com.liutova.avocare.view.fragment.SettingsFragment;

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
        setupUI(mDrawerLayout);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
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
        mDrawerLayout.closeDrawers();
        switch (menuItem.getItemId()){
            case R.id.navigation_item_home:
                replaceFragment(MainFragment.newInstance());
                return true;
            case R.id.navigation_item_history:
                replaceFragment(HistoryFragment.newInstance());
                return true;
            case R.id.navigation_item_favourites:
                replaceFragment(FavouritesFragment.newInstance());
                return true;
            case R.id.navigation_item_my_alergens:
                replaceFragment(AlergensFragment.newInstance());
                return true;
            case R.id.navigation_item_settings:
                replaceFragment(SettingsFragment.newInstance());
                return true;
            case R.id.navigation_item_help:
                replaceFragment(HelpFragment.newInstance());
                return true;
            case R.id.navigation_item_about:
                replaceFragment(AboutFragment.newInstance());
                return true;
        }
        return false;
    }

    public void changeTitle(String s) {
        mToolbar.setTitle(s);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentById(R.id.container) instanceof MainFragment) {
            super.onBackPressed();
        }
        replaceFragment(MainFragment.newInstance());
    }

    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    Helper.hideKeyboard(BaseActivity.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

}

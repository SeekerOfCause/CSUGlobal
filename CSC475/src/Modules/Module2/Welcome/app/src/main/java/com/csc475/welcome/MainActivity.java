package com.csc475.welcome;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(AppCompatResources.getDrawable(toolbar.getContext(), R.drawable.menuicon));
        setSupportActionBar(toolbar);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        mDrawer = findViewById(R.id.drawer_layout);
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);

    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {



        if (menuItem.getItemId() == R.id.nav_second_fragment) {
            Toast.makeText(this, "Its the Eiffel Tower", Toast.LENGTH_SHORT).show();
        }
        else if (menuItem.getItemId() == R.id.nav_third_fragment) {
            Toast.makeText(this, "Its the Tower Bridge", Toast.LENGTH_SHORT).show();
        }
        else if (menuItem.getItemId() == R.id.nav_fourth_fragment) {
            Toast.makeText(this, "Its the Brooklyn Bridge", Toast.LENGTH_SHORT).show();
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        setTitle("Welcome");
        // Close the navigation drawer
        mDrawer.closeDrawers();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        if (item.getItemId() == android.R.id.home) {
            mDrawer.openDrawer(GravityCompat.START);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
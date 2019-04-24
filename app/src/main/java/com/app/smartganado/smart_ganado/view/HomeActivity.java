package com.app.smartganado.smart_ganado.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.smartganado.smart_ganado.R;
import com.app.smartganado.smart_ganado.model.vo.UserApp;
import com.app.smartganado.smart_ganado.utilities.Utilities;
import com.app.smartganado.smart_ganado.view.fragment.CalendarFragment;
import com.app.smartganado.smart_ganado.view.fragment.EventsFragment;
import com.app.smartganado.smart_ganado.view.fragment.TasksFragment;


public class HomeActivity extends AppCompatActivity {

    private ImageView userImage;
    private TextView editTextName, editTextEmail;

    private UserApp user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navigationViewListener);


        BottomNavigationView navigationViewHome = findViewById(R.id.navigationHome);
        navigationViewHome.setOnNavigationItemSelectedListener(navigationBottonListener);

        loadFragment(new TasksFragment());

        if (user == null)
            user = (UserApp) getIntent().getSerializableExtra("user");//Getting user

        userImage = findViewById(R.id.userImage);
        editTextName = findViewById(R.id.textViewName);
        editTextEmail = findViewById(R.id.textViewEmail);

        try {
            userImage.setImageBitmap(Utilities.byteToBitmap(user.getPhoto()));
            editTextName.setText(user.getName());
            editTextEmail.setText(user.getEmail());
        } catch (Exception e) {
        }


        Toast.makeText(getApplicationContext(), "Bienvenido Sr: " + user.getName(), Toast.LENGTH_LONG).show();
    }

    public void openEventsModule(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewEventActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void openTankModule(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewTankActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void openCattleModule(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewCattleActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

    public void openEstatesModule(View view) {
        Intent intent = new Intent(getApplicationContext(), ViewEstateActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            // super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Snackbar.make(getCurrentFocus(), "Vuelve pronto", Snackbar.LENGTH_LONG).show();

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    //Navigation View Listener
    public NavigationView.OnNavigationItemSelectedListener navigationViewListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            int id = menuItem.getItemId();
            Intent intent = null;

            if (id == R.id.nav_profile) {
                intent = new Intent(getApplicationContext(), NewUserActivity.class);
                intent.putExtra("Info", user);
            } else if (id == R.id.nav_backups) {
                intent = new Intent(getApplicationContext(), ViewProfileActivity.class);
            } else if (id == R.id.nav_appFuntions) {
                intent = new Intent(getApplicationContext(), ViewProfileActivity.class);
            } else if (id == R.id.nav_config) {
                intent = new Intent(getApplicationContext(), ViewProfileActivity.class);
            } else if (id == R.id.nav_manual) {
                intent = new Intent(getApplicationContext(), ViewProfileActivity.class);
            } else if (id == R.id.nav_logout) {
                intent = new Intent(getApplicationContext(), LoginActivity.class);
            }

            Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();

            if (intent != null)
                startActivity(intent);


            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return false;
        }
    };

    //Navigation Botton View Listener
    public BottomNavigationView.OnNavigationItemSelectedListener navigationBottonListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment fragment = null;
            switch (menuItem.getItemId()) {
                case R.id.fragment_task:
                    fragment = new TasksFragment();
                    break;
                case R.id.fragment_calendar:
                    fragment = new CalendarFragment();
                    break;
                case R.id.fragment_events:
                    fragment = new EventsFragment();
                    break;
            }
            return loadFragment(fragment);
        }
    };

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
            return true;
        }
        return false;
    }


}

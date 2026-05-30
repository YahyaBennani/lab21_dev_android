package ensa.ma.sensors;

import android.hardware.Sensor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import ensa.ma.sensors.fragments.ActivityRecognitionFragment;
import ensa.ma.sensors.fragments.CompassFragment;
import ensa.ma.sensors.fragments.MotionSensorFragment;
import ensa.ma.sensors.fragments.SensorGraphFragment;
import ensa.ma.sensors.fragments.SensorsListFragment;
import ensa.ma.sensors.fragments.StepCounterFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            openFragment(new SensorsListFragment());
            navigationView.setCheckedItem(R.id.menu_sensors);
        }
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_sensors) {
            openFragment(new SensorsListFragment());
        } else if (id == R.id.menu_temperature) {
            openFragment(SensorGraphFragment.newInstance(
                    Sensor.TYPE_AMBIENT_TEMPERATURE,
                    "Température ambiante",
                    "FIRST_VALUE"));
        } else if (id == R.id.menu_humidity) {
            openFragment(SensorGraphFragment.newInstance(
                    Sensor.TYPE_RELATIVE_HUMIDITY,
                    "Humidité relative",
                    "FIRST_VALUE"));
        } else if (id == R.id.menu_proximity) {
            openFragment(SensorGraphFragment.newInstance(
                    Sensor.TYPE_PROXIMITY,
                    "Capteur de proximité",
                    "FIRST_VALUE"));
        } else if (id == R.id.menu_magnetic) {
            openFragment(SensorGraphFragment.newInstance(
                    Sensor.TYPE_MAGNETIC_FIELD,
                    "Champ magnétique",
                    "MAGNITUDE"));
        } else if (id == R.id.menu_accelerometer) {
            openFragment(MotionSensorFragment.newInstance(
                    Sensor.TYPE_ACCELEROMETER,
                    "Accéléromètre : x, y, z"));
        } else if (id == R.id.menu_gravity) {
            openFragment(MotionSensorFragment.newInstance(
                    Sensor.TYPE_GRAVITY,
                    "Gravité : x, y, z"));
        } else if (id == R.id.menu_gyroscope) {
            openFragment(MotionSensorFragment.newInstance(
                    Sensor.TYPE_GYROSCOPE,
                    "Gyroscope : rad/s"));
        } else if (id == R.id.menu_steps) {
            openFragment(new StepCounterFragment());
        } else if (id == R.id.menu_compass) {
            openFragment(new CompassFragment());
        } else if (id == R.id.menu_activity) {
            openFragment(new ActivityRecognitionFragment());
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

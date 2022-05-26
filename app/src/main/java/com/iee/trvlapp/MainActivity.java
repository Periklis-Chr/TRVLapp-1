package com.iee.trvlapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.iee.trvlapp.FirestoreEntities.Costumers;
import com.iee.trvlapp.databinding.ActivityMainBinding;
import com.iee.trvlapp.roomEntities.AppDatabase;
import com.iee.trvlapp.roomEntities.CityHotels;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;
import com.iee.trvlapp.ui.Offices.UpdateOfficesFragment;
import com.iee.trvlapp.ui.Tours.ToursFragment;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static AppDatabase appDatabase;
    public static FirebaseFirestore appDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Database Reference and Initialization

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "AppDb").allowMainThreadQueries().createFromAsset("db/AppDb.db").build();
        appDb = FirebaseFirestore.getInstance();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_offices, R.id.nav_tours, R.id.nav_packages, R.id.nav_costumers, R.id.settings, R.id.nav_hotels, R.id.nav_about, R.id.nav_help)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //Support for Changing Language to Greek

        binding.changeLangGr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Locale locale = new Locale("gr");
                Resources resources = MainActivity.this.getResources();
                Configuration configuration = resources.getConfiguration();
                configuration.setLocale(locale);
                resources.updateConfiguration(configuration, resources.getDisplayMetrics());

                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        //Support for Changing Language to English

        binding.changeLangEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Locale locale = new Locale("");
                Resources resources = MainActivity.this.getResources();
                Configuration configuration = resources.getConfiguration();
                configuration.setLocale(locale);
                resources.updateConfiguration(configuration, resources.getDisplayMetrics());
                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
package com.iee.trvlapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
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

import com.google.firebase.firestore.FirebaseFirestore;
import com.iee.trvlapp.databinding.ActivityMainBinding;
import com.iee.trvlapp.roomEntities.AppDatabase;
import com.iee.trvlapp.roomEntities.CityHotels;
import com.iee.trvlapp.roomEntities.Offices;
import com.iee.trvlapp.roomEntities.Packages;
import com.iee.trvlapp.roomEntities.Tours;
import com.iee.trvlapp.ui.Offices.UpdateOfficesFragment;
import com.iee.trvlapp.ui.Tours.ToursFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    public static AppDatabase appDatabase;
    public static FirebaseFirestore appDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "TVRL_db").allowMainThreadQueries().createFromAsset("db/TVRL_db_v2").build();
        appDb = FirebaseFirestore.getInstance();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_offices, R.id.nav_tours, R.id.nav_packages, R.id.nav_costumers, R.id.nav_AboutUs)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
//        fillRoomDatabase();
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


//    public void fillRoomDatabase() {
//
//        // office Models
//
//        Offices office1 = new Offices(1, "Touristas", "Αντιγονιδων 21");
//        Offices office2 = new Offices(2, "DionTours", "Μητροπόλεως 98");
//        Offices office3 = new Offices(3, "Grecos.gr Travel", "Ναυάρχου Κουντουριώτου");
//        Offices office4 = new Offices(4, "Zorpidis", "Εγνατία 76");
//        Offices office5 = new Offices(5, "Prima Holidays", "Κλεάνθους 38");
//
//        Offices[] officesModelsArray = {office1, office2, office3, office4, office5};
//
//        // Tours models
//
//
//        Tours tour1 = new Tours(1, "London", "England", 5, "ByRoad");
//        Tours tour2 = new Tours(2, "Paris", "France", 8, "ByPlane");
//        Tours tour3 = new Tours(3, "Tokyo", "Japan", 6, "ByPlane");
//        Tours tour4 = new Tours(4, "Vienna", "Austria", 5, "ByRoad");
//        Tours tour5 = new Tours(5, "New York", "USA", 10, "Cruise");
//        Tours[] toursModelsArray = {tour1, tour2, tour3, tour4, tour5};
//        // Package models
//
//        Packages package1 = new Packages(1, 5, 4, 7, 250.0);
//        Packages package2 = new Packages(2, 4, 3, 8, 900.0);
//        Packages package3 = new Packages(3, 3, 2, 5, 500.0);
//        Packages package4 = new Packages(4, 2, 1, 6, 300.0);
//        Packages package5 = new Packages(5, 1, 5, 3, 1400.0);
//
//        Packages[] packagesModelsArray = {package1, package2, package3, package4, package5};
//
//
//        //new york
//
//        CityHotels cityHotels1 = new CityHotels(1, "The Lucerne Hotel", "Gouverneur Street", 5, 5);
//        CityHotels cityHotels2 = new CityHotels(2, "Hotel Edison", "Wall Street.", 4, 5);
//        CityHotels cityHotels3 = new CityHotels(3, " Ink 48 Hotel", "Pearl Street", 3, 5);
////paris
//        CityHotels cityHotels4 = new CityHotels(4, "Hotel Raphael", "Rue Oberkampf.", 5, 2);
//        CityHotels cityHotels5 = new CityHotels(5, "Ibis Styles ", "Rue Saint-Rustique.", 4, 2);
//        CityHotels cityHotels6 = new CityHotels(6, " Citadines", "Avenue Montaigne.", 3, 2);
////vienna
//        CityHotels cityHotels7 = new CityHotels(7, "Kaiserhof Wien", "Julius-Ficker-Straße", 5, 4);
//        CityHotels cityHotels8 = new CityHotels(8, "Hilton Vienna", "Fickeysstraße ", 4, 4);
//        CityHotels cityHotels9 = new CityHotels(9, "Andaz Vienna", "Blutgasse", 3, 4);
////London
//        CityHotels cityHotels10 = new CityHotels(10, "London Marriott ", "Oxford Street", 5, 1);
//        CityHotels cityHotels11 = new CityHotels(11, "The Resident ", " Abbey Road ", 4, 1);
//        CityHotels cityHotels12 = new CityHotels(12, "The Gate", "Baker Street", 3, 1);
////tokyo
//        CityHotels cityHotels13 = new CityHotels(13, "Shibuya", "Kabukichō Ichiban-gai", 5, 3);
//        CityHotels cityHotels14 = new CityHotels(14, "Mitsui", "Godzilla Road", 4, 3);
//        CityHotels cityHotels15 = new CityHotels(15, "JR Kyushu", "Omoide Yokocho", 3, 3);
//        CityHotels[] cityHotelsModelsArray = {cityHotels1, cityHotels2, cityHotels3, cityHotels4, cityHotels5,cityHotels6, cityHotels7, cityHotels8, cityHotels9, cityHotels10,cityHotels11, cityHotels12, cityHotels13, cityHotels14, cityHotels15};
//
//        for (int i = 0; i < 5; i++) {
//            MainActivity.appDatabase.officesDao().addOffice(officesModelsArray[i]);
//
//        }
//
//        for (int j = 0; j < 5; j++) {
//            MainActivity.appDatabase.toursDao().addTour(toursModelsArray[j]);
//        }
//
//        for (int k = 0; k < 5; k++) {
//            MainActivity.appDatabase.packagesDao().addPackage(packagesModelsArray[k]);
//        }
//        for (int l = 0; l < 15; l++) {
//            MainActivity.appDatabase.cityHotelsDao().addCityHotel(cityHotelsModelsArray[l]);
//        }
//
//    }


}
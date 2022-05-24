package com.mirea.course_work;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.mirea.course_work.databinding.ActivityMainBinding;
import com.mirea.course_work.ui.home.App;
import com.mirea.course_work.ui.home.HomeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UniversityDao dao = App.getInstance().getDatabase().universityDao();
        University mirea = new University("МИРЭА", true, true, "г. Москва", 1);
        dao.insert(mirea);
        University mgu = new University("МГУ", true, true, "г. Москва", 1);
        dao.insert(mgu);
        University spbgu = new University("СПбГУ", false, true, "г. Санкт - Петербург", 1);
        dao.insert(spbgu);
        University urfu = new University("УрФУ", true, true, "г. Екатеринбург", 1);
        dao.insert(urfu);
        University ranhigs = new University("РАНХиГС", true, false, "г. Москва", 1);
        dao.insert(ranhigs);

        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
/*
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, homeFragment).commit();
        }
*/
    }


    @Override
    public void onBackPressed() {}
}
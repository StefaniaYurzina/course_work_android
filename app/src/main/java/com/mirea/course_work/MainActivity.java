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

    public void newThread(Runnable runnable){
        Thread t = new Thread(runnable);
        t.start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UniversityDao dao = App.getInstance().getDatabase().universityDao();
        University mirea = new University("МИРЭА", true, true, "г. Москва", R.drawable.mirea);
        University mgu = new University("МГУ", true, true, "г. Москва", R.drawable.mgu);
        University spbgu = new University("СПбГУ", false, true, "г. Санкт - Петербург", R.drawable.spbgu);
        University urfu = new University("УрФУ", true, true, "г. Екатеринбург", R.drawable.urfu);
        University ranhigs = new University("РАНХиГС", true, false, "г. Москва", R.drawable.ranhigs);

        newThread(() -> {
            dao.insert(mirea);
            dao.insert(mgu);
            dao.insert(spbgu);
            dao.insert(urfu);
            dao.insert(ranhigs);
        });

        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_table, R.id.navigation_profile)
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
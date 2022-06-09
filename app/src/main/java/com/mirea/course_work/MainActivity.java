package com.mirea.course_work;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.mirea.course_work.databinding.ActivityMainBinding;
import com.mirea.course_work.ui.home.App;
import com.mirea.course_work.ui.home.HomeFragment;
import com.mirea.course_work.ui.profile.ProfileFragment;
import com.mirea.course_work.ui.table.TableFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public void newThread(Runnable runnable){
        Thread t = new Thread(runnable);
        t.start();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_table:
                    loadFragment(TableFragment.getInstance());
                    return true;
                case R.id.navigation_home:
                    loadFragment(HomeFragment.getInstance());
                    return true;
                case R.id.navigation_profile:
                    loadFragment(ProfileFragment.getInstance());
                    return true;
            }
            return false;
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadFragment(androidx.fragment.app.Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // ft.setCustomAnimations(R.anim.alphaup,R.anim.alphadown);

        ArrayList<Fragment> fragments = (ArrayList<Fragment>) getSupportFragmentManager().getFragments();
        for (Fragment tempfragment : fragments) {
            ft.hide(tempfragment);

            if (!fragment.isHidden())
                ft.add(R.id.fragment_container, fragment);
            else
                ft.show(fragment);


            ft.commitAllowingStateLoss();
        }
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
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);

//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_table, R.id.navigation_profile)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
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
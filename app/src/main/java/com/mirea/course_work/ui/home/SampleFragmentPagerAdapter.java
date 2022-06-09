package com.mirea.course_work.ui.home;

import androidx.fragment.app.FragmentManager;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] {"Информация", "Специальности", "Общежития"};
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) return MireaPageFragment.newInstance(position + 1);
        else if (position == 1) return SpecialtiesPageFragment.newInstance(position + 1);
        else return DormPageFragment.newInstance(position + 1);
    }

    @Override public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}

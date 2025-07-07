package com.example.parkeerapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Ganti sesuai fragment kamu
        switch (position) {
            case 0:
                return new UpcomingFragment(); // kamu harus buat BookingFragment.java
            case 1:
                return new HistoryFragment(); // kamu harus buat HistoryFragment.java
            default:
                return new UpcomingFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // jumlah tab/fragments
    }
}


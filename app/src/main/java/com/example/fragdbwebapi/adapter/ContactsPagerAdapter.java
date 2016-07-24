package com.example.fragdbwebapi.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fragdbwebapi.ContactDetailFragment;
import com.example.fragdbwebapi.MainActivity;

/**
 * Created by Promlert on 7/24/2016.
 */
public class ContactsPagerAdapter extends FragmentStatePagerAdapter {

    public ContactsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ContactDetailFragment fragment = ContactDetailFragment.newInstance(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return MainActivity.mContactsList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return MainActivity.mContactsList.get(position).name;
    }
}

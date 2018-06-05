package com.example.dnghwls7.conn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by dnghwls7 on 2018-05-15.
 * 탭이 변할때마다 position을 받아 Fragment를 전환
 */
public class PageAdapter extends FragmentStatePagerAdapter{

    int numberofTabs;

    HomeFragment homeFragment;
    ControlFragment controlFragment;
    SettingFragment settingFragment;

    public PageAdapter(FragmentManager fm,int numberofTabs,HomeFragment homeFragment,ControlFragment controlFragment,SettingFragment settingFragment){
        super(fm);
        this.numberofTabs = numberofTabs;

        this.homeFragment = homeFragment;
        this.controlFragment = controlFragment;
        this.settingFragment = settingFragment;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return homeFragment;
            case 1:
                return controlFragment;
            case 2:
                BoardFragment boardFragment = new BoardFragment();
                return boardFragment;
            case 3:
                return settingFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberofTabs;
    }


}

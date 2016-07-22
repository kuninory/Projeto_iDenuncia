package com.example.idenuncia.idenuncia;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import layout.DenunciasFragment;
import layout.MinhasDenunciasFragment;
import layout.UsuarioFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MinhasDenunciasFragment tab1 = new MinhasDenunciasFragment();
                return tab1;
            case 1:
                DenunciasFragment tab2 = new DenunciasFragment();
                return tab2;
            case 2:
                UsuarioFragment tab3 = new UsuarioFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}

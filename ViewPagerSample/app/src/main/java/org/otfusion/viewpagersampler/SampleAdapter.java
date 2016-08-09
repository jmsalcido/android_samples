package org.otfusion.viewpagersampler;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

public class SampleAdapter extends FragmentPagerAdapter {

    public SampleAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return EditorFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 10;
    }
}

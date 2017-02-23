package org.otfusion.viewpagersampler;

import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

public class SampleAdapter extends FragmentPagerAdapter {

    private Context context;

    public SampleAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public EditorFragment getItem(int position) {
        return EditorFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getItem(position).getTitle();
    }
}

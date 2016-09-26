package newsday.zhuoxing.com.newsapp.adapter;

import  android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1.
 */
public class NewTabPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> tabTitleList;
    public NewTabPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tabTitleList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tabTitleList = tabTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleList.get(position);
    }
}

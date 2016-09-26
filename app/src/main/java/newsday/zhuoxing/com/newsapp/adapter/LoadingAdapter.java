package newsday.zhuoxing.com.newsapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class LoadingAdapter extends PagerAdapter {
    private List<View> pagers;

    public LoadingAdapter(List<View> pagers) {
        this.pagers = pagers;
    }

    @Override
    public int getCount() {
        return pagers.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(pagers.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(pagers.get(position),0);
        return pagers.get(position);
    }
}

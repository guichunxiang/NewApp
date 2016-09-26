package newsday.zhuoxing.com.newsapp.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/9.
 */
public class LoadingTwoAdapter extends PagerAdapter {
    private ArrayList<ImageView> imageList;

    public LoadingTwoAdapter(ArrayList<ImageView> imageList) {
        this.imageList = imageList;
    }


    /**
     * 填充image的方法
     * */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imageList.get(position));
        return imageList.get(position);
    }
    /**
     * 销毁image的方法
     * */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageList.get(position));

    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}

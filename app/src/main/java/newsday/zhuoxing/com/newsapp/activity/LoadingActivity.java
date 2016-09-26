package newsday.zhuoxing.com.newsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import newsday.zhuoxing.com.newsapp.R;
import newsday.zhuoxing.com.newsapp.base.BaseActivity;

/**
 * Created by Administrator on 2016/8/31.
 */
public class LoadingActivity extends Activity implements
        View.OnClickListener,
        ViewPager.OnPageChangeListener,
        Animation.AnimationListener{
    private ViewPager viewPager;
    private ImageView imageView1,imageView2,imageView3;
    private List<View> views;
    private List<ImageView> imageViews;
    private Button button;

    public void initView(){
        imageViews = new ArrayList<>();
        imageView1 = new ImageView(this);
        imageView1.setBackground(getResources().getDrawable(R.drawable.lead_1));
        imageViews.add(imageView1);
        imageView2 = new ImageView(this);
        imageView2.setBackground(getResources().getDrawable(R.drawable.lead_2));
        imageViews.add(imageView2);
        imageView3 = new ImageView(this);
        imageView3.setBackground(getResources().getDrawable(R.drawable.lead_3));
        imageViews.add(imageView3);
        button = (Button)findViewById(R.id.bt_loading);
        button.setOnClickListener(this);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_loading);
        viewPager = (ViewPager)findViewById(R.id.viewPager_loading);
        viewPager.addOnPageChangeListener(this);
        initView();

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return imageViews.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
               container.addView(
                       imageViews.get(position),position);
                return container.getChildAt(position);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
               container.removeViewAt(position);
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return super.getPageTitle(position);
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
            if (position == 2) {
                button.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_button);
                button.startAnimation(animation);
                animation.setAnimationListener(this);
            }
            else
                button.setVisibility(View.GONE);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();// 结束loading

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

}

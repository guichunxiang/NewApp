package newsday.zhuoxing.com.newsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import newsday.zhuoxing.com.newsapp.R;
import newsday.zhuoxing.com.newsapp.adapter.LoadingTwoAdapter;
import newsday.zhuoxing.com.newsapp.util.SharedUtil;

/**
 * Created by Administrator on 2016/9/9.
 */
public class LoadingTwoActivity extends Activity {
    private ViewPager viewPager_guide;
    private ImageView icon1,icon2,icon3,icon4;
    private TextView number_guide;
    private ArrayList<ImageView> imageList=new ArrayList<ImageView>();
    private int []imageId={
            R.drawable.welcome,R.drawable.bd,R.drawable.wy,R.drawable.small    };
    private LoadingTwoAdapter adapter;
    private SharedUtil sharedUtil;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_loadingtwo);
        sharedUtil=new SharedUtil(this);
        if (sharedUtil.getIsFirstRun()){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        viewPager_guide= (ViewPager) findViewById(R.id.viewPager_guide);
        icon1= (ImageView) findViewById(R.id.icon1_guide);
        icon2= (ImageView) findViewById(R.id.icon2_guide);
        icon3= (ImageView) findViewById(R.id.icon3_guide);
        icon4= (ImageView) findViewById(R.id.icon4_guide);
        number_guide= (TextView) findViewById(R.id.number_guide);
        initData();
        initData();
        viewPager_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position){
                    case 0:
                        icon1.setAlpha(1f);
                        icon2.setAlpha(0.3f);
                        icon3.setAlpha(0.3f);
                        icon4.setAlpha(0.3f);

                        break;
                    case 1:
                        icon1.setAlpha(0.3f);
                        icon2.setAlpha(1f);
                        icon3.setAlpha(0.3f);
                        icon4.setAlpha(0.3f);
                        break;
                    case 2:
                        icon1.setAlpha(0.3f);
                        icon2.setAlpha(0.3f);
                        icon3.setAlpha(1f);
                        icon4.setAlpha(0.3f);
                        break;
                    case 3:
                        icon1.setAlpha(0.3f);
                        icon2.setAlpha(0.3f);
                        icon3.setAlpha(0.3f);
                        icon4.setAlpha(1f);

                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                pos=position;
                if (position==3){
                    i=3;
                    number_guide.setVisibility(View.VISIBLE);
                    handler.sendEmptyMessage(0);
                }else {
                    number_guide.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    int i=3;
    int pos;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (pos==3) {

                number_guide.setText(i + "秒后进入");
                i--;

                if (i > 0) {
                    handler.sendEmptyMessageDelayed(0, 1000);
                } else {
                    //存入以引导数据，跳转页面

                    sharedUtil.putIsFirstRun();
                    Intent intent=new Intent(LoadingTwoActivity.this,WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    };
    private void initData() {
        for (int i=0;i<imageId.length;i++){
            ImageView imageview=new ImageView(getBaseContext());
            imageview.setImageResource(imageId[i]);//设置图片ID
            imageview.setScaleType(ImageView.ScaleType.FIT_XY);//设置图片拉伸
            imageList.add(imageview);//将图片信息添加到集合列表
        }
        adapter=new LoadingTwoAdapter(imageList);//初始化adapter
        viewPager_guide.setAdapter(adapter);//设置设配器
    }
}

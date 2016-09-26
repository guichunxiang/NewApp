package newsday.zhuoxing.com.newsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import newsday.zhuoxing.com.newsapp.R;

/**
 * Created by Administrator on 2016/8/31.
 */
public class WelcomeActivity extends Activity implements Animation.AnimationListener{
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        initAnimation();
    }
    private void initView() {
        imageView = (ImageView)findViewById(R.id.iv_welcome);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_loading);
        imageView.startAnimation(animation);
        animation.setAnimationListener(this);
    }

    private void initAnimation() {

    }
    /*
    alt + insert
     */
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();// 结束welcome

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}

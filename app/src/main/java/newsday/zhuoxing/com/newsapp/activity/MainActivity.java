package newsday.zhuoxing.com.newsapp.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import newsday.zhuoxing.com.newsapp.Fragment.HotFragment;
import newsday.zhuoxing.com.newsapp.Fragment.NewsFragment;
import newsday.zhuoxing.com.newsapp.Fragment.SearchFragment;
import newsday.zhuoxing.com.newsapp.R;
import newsday.zhuoxing.com.newsapp.base.BaseActivity;

/**
 * Created by Administrator on 2016/9/1.
 */
public class MainActivity  extends BaseActivity implements View.OnClickListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private RadioButton rb_news, rb_hot, rb_search;
    private Fragment[] fragments = new Fragment[3];
    private Toolbar toolbar;
    private DrawerLayout loading_drawer;
    private NavigationView loading_naView;
    private TextView textView;
    private View heardView;


    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_fragment);
        // 初始化
        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void initView() {
        /**
         *底部Button
         */
        rb_news = (RadioButton) findViewById(R.id.main_radio_new);
        rb_hot = (RadioButton) findViewById(R.id.main_radio_hot);
        rb_search = (RadioButton) findViewById(R.id.main_radio_search);
        rb_news.setOnClickListener(this);
        rb_hot.setOnClickListener(this);
        rb_search.setOnClickListener(this);

        toolbar = (Toolbar)findViewById(R.id.home_toolbar);
       toolbar.setTitle("");
        setSupportActionBar(toolbar);
        loading_drawer = (DrawerLayout)findViewById(R.id.dl_loading_drawer);
        loading_naView = (NavigationView)findViewById(R.id.loading_naView);
        heardView = loading_naView.getHeaderView(0);
        textView = (TextView)heardView.findViewById(R.id.me_text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoadingThreeActivity.class);
                startActivity(intent);
            }
        });
        /**
         * 呈现Toolbar左上角的按钮
         */
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 对Drawer监听 这个监听别人已经实现了 我们直接先使用 需要注意的是我们需要关联到我们的ToolBar
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,loading_drawer,toolbar,R.string.app_name,R.string.app_name);
        // 同步状态
        drawerToggle.syncState();
        // 对我们的DrawerLayout设置上监听 关于滑动的动画 ActionBarDrawerToggle在做实现  我们不需要去管理
        loading_drawer.addDrawerListener(drawerToggle);
        // 隐藏的View 呈现出来以后 我们是可能点击的 在这边想有相应的点击处理操作 那么直接想到监听者
        loading_naView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    // 选择情况下执行的相关操作

                    case R.id.drawer_collen:
                        Intent intent1 = new Intent(MainActivity.this,CollectActivity.class);
                        startActivity(intent1);
                        break;
                    case R.id.drawer_me:
                        Toast.makeText(MainActivity.this,"关于我们",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.drawer_settings:
                        Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                        startActivity(intent);
                        break;
                }
                // 注意返回值
                return true;
            }
        });

        choiceFragment(0);




    }


    private void initShared(){
            ShareSDK.initSDK(this);
            OnekeyShare oks = new OnekeyShare();
            oks.disableSSOWhenAuthorize();
            oks.setTitle("标题");
            oks.setTitleUrl("http://sharesdk.cn");
            oks.setText("我是分享文本");
            oks.setUrl("http://sharesdk.cn");
            oks.setComment("我是测试评论文本");
            oks.setSite(getString(R.string.app_name));
            oks.setSiteUrl("http://sharesdk.cn");
            oks.show(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        initShared();
        return true;
    }
    // 隐藏掉所有的Fragment
    private void hideAllFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            if (fragments[i] != null)
                fragmentTransaction.hide(fragments[i]);
        }
        fragmentTransaction.commit();
    }

    // 切换Fragment
    private void choiceFragment(int index) {
        // 隐藏掉所有的Fragment
        hideAllFragment();
        // 开启事务
        fragmentTransaction = fragmentManager.beginTransaction();
        if (fragments[index] == null) {
            switch (index) {
                case 0:
                    fragments[index] = new NewsFragment();
                    fragmentTransaction.add(R.id.news_layout, fragments[index]);
                    break;
                case 1:
                    fragments[index] = new HotFragment();
                    fragmentTransaction.add(R.id.news_layout, fragments[index]);
                    break;
                case 2:
                    fragments[index] = new SearchFragment();
                    fragmentTransaction.add(R.id.news_layout, fragments[index]);
                    break;
            }
        } else {
            fragmentTransaction.show(fragments[index]);
        }
        // 提交事务
        fragmentTransaction.commit();
    }

    @Override
    public void afterViewLogic() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_radio_new:
                choiceFragment(0);
                break;
            case R.id.main_radio_hot:
                choiceFragment(1);
                break;
            case R.id.main_radio_search:
                choiceFragment(2);
                break;
//            case R.id.me_text:
//
//                break;



        }

    }
}

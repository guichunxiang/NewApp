package newsday.zhuoxing.com.newsapp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import newsday.zhuoxing.com.newsapp.DB.MySqlExpress;
import newsday.zhuoxing.com.newsapp.R;

/**
 * Created by Administrator on 2016/9/19.
 */
public class NewsInfoActivity extends Activity {
    private WebView webView;
    private ImageView imageView,imageView2;
    private ProgressBar progressBar;
    private WebSettings webSettings;
    private MySqlExpress express;
    private String url,title;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            if ((int)msg.obj == 100)
                progressBar.setVisibility(View.INVISIBLE);
            else{
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress((int)msg.obj);

            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info_item);
        url = getIntent().getExtras().getString("key");
        title = getIntent().getExtras().getString("key1");
        findView();
        webSettings = webView.getSettings();
        webView.loadUrl(url);
        webView.canGoBack();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        recive();
        Click();
        /**
         * 收藏监听
         */
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                express = new MySqlExpress(NewsInfoActivity.this);
                express.addMsg(title,url);
                Log.i("database", url+title);
                Toast.makeText(NewsInfoActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * 接网路数据
     */
    public void recive(){
        dealPB();
        String str = getIntent().getExtras().getString("key");
        webView.loadUrl(str);
    }

    /**
     * 找控件
     */
    public void findView(){
        progressBar = (ProgressBar)findViewById(R.id.news_pb);
        webView = (WebView) findViewById(R.id.news_info_videoView);
        imageView = (ImageView)findViewById(R.id.iv_news_back);
        imageView2 = (ImageView)findViewById(R.id.iv_news_collection);

    }

    /**
     * 返回
     * 监听
     */
    public void Click(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 对ProgressBar的操作
     */
    public void dealPB(){
        webView.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Message message = new Message();
                message.obj = newProgress;
                handler.sendMessage(message);
            }
        });
    }
 }

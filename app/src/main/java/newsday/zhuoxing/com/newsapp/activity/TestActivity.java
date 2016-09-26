package newsday.zhuoxing.com.newsapp.activity;

import android.app.Application;
import android.widget.TextView;

import com.baidu.apistore.sdk.ApiStoreSDK;

/**
 * Created by Administrator on 2016/9/8.
 */
public class TestActivity extends Application {
     TextView textView ;

    @Override
    public void onCreate() {

        ApiStoreSDK.init(this,"203efb4664b29b48370dec61d5b5d86e");
        super.onCreate();


    }



}

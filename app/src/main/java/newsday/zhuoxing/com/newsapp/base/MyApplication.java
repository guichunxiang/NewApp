package newsday.zhuoxing.com.newsapp.base;

import android.app.Application;
import android.util.Log;

import com.android.volley.RequestQueue;

import com.android.volley.toolbox.Volley;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;


/**
 *
 * Created by Administrator on 2016/9/18.
 */
public class MyApplication extends Application {
   // private RequestQueue requestQueue;

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

//    public RequestQueue getRequestQueue() {
//        return requestQueue;
//    }

//    public void setRequestQueue(RequestQueue requestQueue) {
//        this.requestQueue = requestQueue;
//    }

    public static Gson gson;
    private static MyApplication myApplication;
    public static MyApplication getInstance(){
        return myApplication;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ApiStoreSDK.init(this,"203efb4664b29b48370dec61d5b5d86e");
        myApplication = this;
        // 请求队列的实例化
       // requestQueue = Volley.newRequestQueue(getApplicationContext());
        // Gson对象
        gson = new Gson();
        // 1.初始化ImageLoader配置信息 LruCache
        // ImageLoaderConfiguration configuration1 = ImageLoaderConfiguration.createDefault(this);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480,800)
                .threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY-1)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13)
//                .diskCacheSize(50 * 1024 * 1024).diskCacheFileCount(50)
//                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())// default
//                .imageDownloader(new BaseImageDownloader(this))//default
                .imageDecoder(new BaseImageDecoder(true))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())// default
                .writeDebugLogs()
                .build();
        // 2.将配置信息给予我们的ImageLoader对象
        //ImageLoader.getInstance().init(configuration);
        ImageLoader.getInstance().init(configuration);
    }

    /**
     * 应用停止
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        BaseActivity.requestQueue.cancelAll(this);// 停止掉所有的请求
    }
}

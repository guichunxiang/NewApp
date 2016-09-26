package newsday.zhuoxing.com.newsapp.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/8/31.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public static RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        setContentLayout();
        initView();
        afterViewLogic();
    }
    public abstract void  setContentLayout();

    public abstract void  initView();

    public abstract void  afterViewLogic();
    /**
     * 跳转
     */
    public  void startActivity(Class<?> targetAct){
        Intent intent = new Intent(this,targetAct);
        startActivity(intent);
    }
    /**
     * app名称
     */
    public String getAppVersionName(){
        String varName = "";
        try {
            varName = getPackageManager().getPackageInfo(getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return varName;
    }
    /**
     * 版本号获取
     */
    public String getAppVersionCode(){
        int varCode = 0;
        try {
            varCode = getPackageManager().getPackageInfo(getPackageName(),0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return varCode+"";
    }

    @Override
    public void finish() {
        super.finish();
    }
}

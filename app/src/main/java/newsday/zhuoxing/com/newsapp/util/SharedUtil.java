package newsday.zhuoxing.com.newsapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 2016/7/19.
 */
public class SharedUtil {
    private static SharedUtil sInstance;
    private Context context;
    private SharedPreferences preferences;
    public SharedUtil(Context context) {
        this.context = context;
        preferences=context.getSharedPreferences(CommonUtil.SHARE_USER_NAME,Context.MODE_PRIVATE);
        //初始化preferences，参数一表名 ：is frist run,参数二模式为私有的
    }
    public static SharedUtil getsInstance(Context context){
        if (sInstance==null){
            sInstance=new SharedUtil(context);
        }
        return sInstance;
    }
    /**
     * 存入数据的方法
     * */
    public void putIsFirstRun(){
        preferences.edit()//获取到编辑器
                .putBoolean("isFirstRun",true)//将信息添加
                .commit();//提交
    }
    public boolean getIsFirstRun(){
        return preferences.getBoolean("isFirstRun",false);
    }
}

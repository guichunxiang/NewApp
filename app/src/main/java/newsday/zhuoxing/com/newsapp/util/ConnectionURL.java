package newsday.zhuoxing.com.newsapp.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/9/18.
 * 请求一个URL的工具类
 */
public class ConnectionURL {
    /**
     * 百度API地址
     */
    public static final String BAIDU_URL = "http://apis.baidu.com/showapi_open_bus/channel_news/search_news";
    public static final String BAIDU_TAG = "http://apis.baidu.com/showapi_open_bus/channel_news/channel_news";
    public static final String BAIDU_KEY = "203efb4664b29b48370dec61d5b5d86e";
    /**
     * 拼接URL的方法
     */
    public static String findNewsByName(String newName,int page){
        return BAIDU_URL+"?channelName="+changeToUTF(newName+"焦点")+"&page="+page;
    }
    private static String changeToUTF(String newName){
        try {
            newName = URLEncoder.encode(newName,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newName;
    }
}

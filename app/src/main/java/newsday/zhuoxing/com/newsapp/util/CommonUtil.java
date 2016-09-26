package newsday.zhuoxing.com.newsapp.util;

/**
 * Created by lenovo on 2016/7/18.
 */
public interface CommonUtil {

    /** 联网的 ip */
    public static String NETIP = "118.244.212.82";
    /** 联网的路径 */
    public static String NETPATH = "http://" + NETIP + ":9092/newsClient";
    /** SharedPreferences 保存用户名键 */
    public static final String SHARE_USER_NAME = "userName";
    /** SharedPreferences 保存用户名密码 */
    public static final String SHARE_USER_PWD = "userPwd";
    /** SharedPreferences 保存是否第一次登陆 */
    public static final String SHARE_IS_FIRST_RUN = "isFirstRun";
    /** SharedPreferences 存储路径 */
    public static final String SHAREPATH = "news_share";
}

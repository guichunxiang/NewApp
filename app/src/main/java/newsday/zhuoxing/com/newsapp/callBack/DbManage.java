package newsday.zhuoxing.com.newsapp.callBack;

/**
 * Created by Administrator on 2016/9/21.
 */
public class DbManage {
    private String title,url;

    public DbManage(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "DbManage{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

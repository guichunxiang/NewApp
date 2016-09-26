package newsday.zhuoxing.com.newsapp.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * SharedPre 工具类
 */
public class SharedPreUtil {

    private Context context;

    private String lead_sharedP = "lead_data";
    private String datas = "datas";

    public SharedPreUtil(Context context){
        this.context = context ;
    }

    /**
     * 保存引导信息
     */
    public void saveLeadDataToShared(String  versionName){
        SharedPreferences sp = this.context.getSharedPreferences(lead_sharedP,Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sp.edit();
        editor.putString("versionName",versionName);
        editor.commit();
    }

    /**
     *
     * @return versionName
     */
    public String  getLeadDataFromShared(){
        return context.getSharedPreferences(lead_sharedP,0).getString("versionName","");
    }

    public List<String> getFromSharedInfoList(){
        Set<String> list = getFromSharedInfo();
        List<String> tablist = new ArrayList<>();
        for (String tab:list
             ) {
            tablist.add(tab);
        }
        return tablist;
    }

    public Set<String> getFromSharedInfo(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(datas,0);
        Set<String> list = sharedPreferences.getStringSet("",null);
        if (list == null){
            list = new TreeSet<>();
            list.add("互联网");
            list.add("国内");
            list.add("国际");
            list.add("娱乐");
            list.add("军事");
            list.add("游戏");
        }
        return list;
    }
    public void saveToSharedInfo(Set<String> list){
        SharedPreferences sharedPreferences = context.getSharedPreferences(datas,0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("",list);
        editor.commit();
    }

}

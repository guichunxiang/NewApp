package newsday.zhuoxing.com.newsapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import newsday.zhuoxing.com.newsapp.callBack.DbManage;

/**
 * Created by Administrator on 2016/9/20.
 */
public class MySqlExpress {
    SQLiteDatabase db;
    Context context;

    public MySqlExpress(Context context) {
        this.context = context;
        Mydb mydb = new Mydb(context);
        db = mydb.getReadableDatabase();
    }

    /**
     * 添加操作
     */
    public void addMsg(String title, String url) {
        ContentValues contentValues = new ContentValues();
//        contentValues.put("msg",string);
//        db.insert("mysql",null,contentValues);
        if (getMsg(title).size() == 0) {
            contentValues.put("title", title);
            contentValues.put("url",url);
            db.insert("mysql", null, contentValues);
        }
    }

    /**
     * 查询操作
     */
    public List<DbManage> getMsg(String name) {
        Cursor cursor = db.rawQuery("select * from mysql where title Like ?", new String[]{'%' + name + '%'});
        Cursor cursor1 = db.rawQuery("select * from mysql where url Like ?", new String[]{'%' + name + '%'});
        List<DbManage> list = new ArrayList<DbManage>();
        DbManage db;
        while (cursor1.moveToNext()) {
            String title = cursor1.getString(cursor.getColumnIndex("title"));
            String url = cursor1.getString(cursor.getColumnIndex("url"));
            db=new DbManage(title,url);
            list.add(db);
        }
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            db=new DbManage(title,url);
            list.add(db);
        }
        return list;
    }

    public List<DbManage> getAllMeg() {
        Cursor cursor = db.rawQuery("select * from mysql", null);
        List<DbManage> list = new ArrayList<DbManage>();
        DbManage db;
        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String url = cursor.getString(cursor.getColumnIndex("url"));
            db=new DbManage(title,url);
            list.add(db);
        }
        return list;
    }
}
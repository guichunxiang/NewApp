package newsday.zhuoxing.com.newsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import newsday.zhuoxing.com.newsapp.DB.MySqlExpress;
import newsday.zhuoxing.com.newsapp.R;
import newsday.zhuoxing.com.newsapp.callBack.DbManage;

/**
 * Created by Administrator on 2016/9/21.
 */
public class CollectActivity extends Activity {
    private ImageView imageView;
    private ListView listView;
    private MySqlExpress mySqlExpress;
    private List<DbManage> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        mySqlExpress = new MySqlExpress(this);
        listView = (ListView)findViewById(R.id.lv_collect);
        imageView = (ImageView)findViewById(R.id.iv_collect_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setData();
        setListView();
    }

    private void setData(){
        list = new ArrayList<>();
        list = mySqlExpress.getAllMeg();
        List<String> data = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            data.add(list.get(i).getTitle());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,
                        R.layout.item_collect, R.id.item_MyCollect, data);
        listView.setAdapter(arrayAdapter);
    }

    private void setListView(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CollectActivity.this,NewsInfoActivity.class);
                Bundle bundle = new Bundle();
                String url = list.get(position).getUrl();
                bundle.putString("key",url);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}

package newsday.zhuoxing.com.newsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Set;

import newsday.zhuoxing.com.newsapp.R;
import newsday.zhuoxing.com.newsapp.base.BaseActivity;
import newsday.zhuoxing.com.newsapp.callBack.FlowLayoutCallback;
import newsday.zhuoxing.com.newsapp.ui.FlowLayout;
import newsday.zhuoxing.com.newsapp.util.SharedPreUtil;

/**
 * Created by Administrator on 2016/9/5.
 */
public class AddActivity extends BaseActivity implements View.OnClickListener{
    private ImageView imageView;
    private Toolbar toolbar;
    private FlowLayout flowLayout;
    private Set<String> data;
    private ListView listView;
    private String [] sc = {"互联网","体育","娱乐","生活","地理","天文",
            "军事","星座","安卓","热点","八卦","政治","国际"};
    private SharedPreUtil sharedPreUtil;
    private boolean needUpdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentLayout() {

    }

    @Override
    public void initView() {
        toolbar = (Toolbar)findViewById(R.id.add_toolbar);
        imageView = (ImageView)findViewById(R.id.iv_add_back);
        listView = (ListView)findViewById(R.id.lv_add);
        imageView.setOnClickListener(this);
    }
    @Override
    public void afterViewLogic() {
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, sc);
        listView.setAdapter(adapter);
        sharedPreUtil = new SharedPreUtil(this);
        data = sharedPreUtil.getFromSharedInfo();
        flowLayout = (FlowLayout) findViewById(R.id.fl_add);
        flowLayout.getSetData(data);
        flowLayout.setCallback(new FlowLayoutCallback() {
            @Override
            public void afterOnChildClick() {
                needUpdata = true;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                data.add(sc[position]);
                flowLayout.getSetData(data);
                needUpdata = true;
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_add_back:
                Intent intent = new Intent();
                intent.putExtra("needUpdate",needUpdata);
                setResult(101,intent);
                finish();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreUtil.saveToSharedInfo(data);
    }
}

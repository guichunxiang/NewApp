package newsday.zhuoxing.com.newsapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import newsday.zhuoxing.com.newsapp.DB.MySqlExpress;
import newsday.zhuoxing.com.newsapp.R;
import newsday.zhuoxing.com.newsapp.activity.NewsInfoActivity;
import newsday.zhuoxing.com.newsapp.callBack.DbManage;

/**
 * Created by Administrator on 2016/9/20.
 */
public class SearchFragment extends Fragment {
    private EditText editText;
    private ListView listView;
    private ImageView imageView;
    private List<DbManage> list;
    private MySqlExpress mySqlExpress;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    String text = (String)msg.obj;
                    list = mySqlExpress.getMsg(text);
                    List<String> data = new ArrayList<>();
                    for (int i=0;i<list.size();i++){
                        data.add(list.get(i).getTitle());
                    }
                    //list.add(text);
                    ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getActivity(),
                            R.layout.item_news_list,R.id.item_news,data);
                    listView.setAdapter(arrayAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(getActivity(),NewsInfoActivity.class);
                            Bundle bundle = new Bundle();
                            String url = list.get(position).getUrl();
                            bundle.putString("key",url);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_search,container,false);
        mySqlExpress = new MySqlExpress(getActivity());
        editText = (EditText)view.findViewById(R.id.et_search);
        listView = (ListView) view.findViewById(R.id.tv_frag_search_info);
        editText.addTextChangedListener(new myText());
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP){
                    Intent intent = new Intent(getContext(),NewsInfoActivity.class);
                    Bundle bundle = new Bundle();
                    if (editText.length() == 0)
                        bundle.putString("LogoUrl","www");
                        intent.putExtras(bundle);
                        startActivity(intent);
                    return true;
                }
                return false;
            }
        });



        return view;

    }
    class myText implements TextWatcher {
        //文本改变前
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        //文本改变中
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Message message = new Message();
            if (s.length()>0){
                String str = s.toString();
                message.obj = str;
                message.what = 0;

                handler.sendMessage(message);
            }
        }
        //文本改变后
        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    @Override
    public void onDestroy() {
        handler.removeMessages(0);
        super.onDestroy();
    }


}


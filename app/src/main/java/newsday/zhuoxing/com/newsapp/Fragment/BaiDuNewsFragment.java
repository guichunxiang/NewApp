package newsday.zhuoxing.com.newsapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import newsday.zhuoxing.com.newsapp.R;
import newsday.zhuoxing.com.newsapp.activity.NewsInfoActivity;
import newsday.zhuoxing.com.newsapp.activity.WelcomeActivity;
import newsday.zhuoxing.com.newsapp.adapter.NewsContentAdapter;
import newsday.zhuoxing.com.newsapp.adapter.base.BaseLoadingAdapter;
import newsday.zhuoxing.com.newsapp.base.BaseActivity;
import newsday.zhuoxing.com.newsapp.base.MyApplication;
import newsday.zhuoxing.com.newsapp.entity.NewsInfo;
import newsday.zhuoxing.com.newsapp.entity.NewsInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean;
import newsday.zhuoxing.com.newsapp.util.ConnectionURL;

/**
 * Created by Administrator on 2016/9/18.
 */
public class BaiDuNewsFragment extends Fragment {
    private View contentView;
    private String newsName;
    private RequestQueue requestQueue;
    private Gson gson;
    private RecyclerView recyclerView;
    private List<ContentlistBean> contentlistBeen;
    private BaseLoadingAdapter<ContentlistBean> adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private final int ADD_DATA_FLAG = 0; // 上拉加载
    private final int UPDATE_DATA_FLAG = 1; // 下拉刷新
    private int all_page = 0; //所有的页数
    private int nowPage = 1; //当前页数

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragement_news_info,container,false);
        //获取传入的参数
        newsName = getArguments().getString("newName");
        requestQueue = BaseActivity.requestQueue;
        gson = MyApplication.gson;
        Log.i("TAG", "onCreateView: ");
        getBaiDuNewsInfo(1,1);
        return contentView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.baiDu_recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.baiDu_fragment_swipeRefreshLayout);
        // 下拉刷新的色彩配置 最多课可以是四种颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.text_color_blue,
                R.color.text_color_green,
                R.color.text_color_red);
//        getBaiDuNewsInfo(UPDATE_DATA_FLAG, 1);
        // 下拉监听

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() { // 下拉刷新时的回调
                // TODO 重新获取网络数据
                getBaiDuNewsInfo(UPDATE_DATA_FLAG, 1);
            }
        });
        contentlistBeen = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new NewsContentAdapter(contentlistBeen, getActivity(), recyclerView);
        adapter.setOnLoadingListener(new BaseLoadingAdapter.OnLoadingListener() {
            @Override
            public void onLoading() {
                getBaiDuNewsInfo(ADD_DATA_FLAG, nowPage);

            }
        });
        /**
         * 回调方法设置头部新闻的监听
         * 进行跳转到WebView上显示
         */
        adapter.setOnRecycleViewClick(new BaseLoadingAdapter.OnRecycleViewClick() {
            @Override
            public void onClickListener(int position) {
                String str1 = contentlistBeen.get(position).getTitle();
               String str = contentlistBeen.get(position).getLink();
                Intent intent = new Intent(getActivity(),NewsInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key",str);
                bundle.putString("key1",str1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
//        getBaiDuNewsInfo(ADD_DATA_FLAG, nowPage);// 添加上一页数据


        super.onViewCreated(view, savedInstanceState);

    }

    /**
     * 获取数据
     */
    private void getBaiDuNewsInfo(final int getDataType,int pager){
        if (all_page > 0 && all_page > nowPage){
            swipeRefreshLayout.setRefreshing(false);
            adapter.setLoadingNoMove();
        }
        StringRequest stringRequest = new StringRequest(ConnectionURL.findNewsByName(newsName, pager), new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                        Log.i("aaa",response);

                    NewsInfo newsInfo = new Gson().fromJson(response, NewsInfo.class);
                    Log.i("111",newsInfo.toString());
                    contentlistBeen = newsInfo.getShowapi_res_body().getPagebean().getContentlist();
                    all_page = newsInfo.getShowapi_res_body().getPagebean().getAllPages();
                    onGetDataSuccess(getDataType);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                getDefult(getDataType);
                Log.i("----",volleyError.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("apikey", ConnectionURL.BAIDU_KEY);
                return map;
            }
        };
        requestQueue.add(stringRequest);
        requestQueue.start();
    }
    private void getDefult(int getDataType){
        switch (getDataType){
            case ADD_DATA_FLAG:
                adapter.setLoadingError();
                break;
            case UPDATE_DATA_FLAG:
                swipeRefreshLayout.setRefreshing(false);
                break;
        }
    }

    /**
     * 获取网络数据成功的时候
     *
     * @param getDataType
     */
    private void onGetDataSuccess(int getDataType) {
        if (contentlistBeen == null || contentlistBeen.size() == 0)
            return;
        Iterator<ContentlistBean> iterator = contentlistBeen.iterator();
        ContentlistBean temp = null;
        // 遍历数据源 并且把所有的没有图片地址的信息删除掉
        while (iterator.hasNext()) {
            temp = iterator.next();
            if (temp == null || temp.getImageurls().size() == 0 || temp.getImageurls().get(0).getUrl() == null)
                iterator.remove();
        }

        switch (getDataType) {
            case ADD_DATA_FLAG:
                adapter.setLoadingComplete();
                if (contentlistBeen != null)
                    adapter.addAll(contentlistBeen);
                nowPage++;//当前页数需要做一个增加
                break;
            case UPDATE_DATA_FLAG:
                if (contentlistBeen != null) {
                    adapter.clearAll();
                    adapter.addAll(contentlistBeen);
                }
                swipeRefreshLayout.setRefreshing(false);//将是否刷新更改为false 以消除掉显示的小球
                nowPage = 2;
                break;
        }
    }
}

package newsday.zhuoxing.com.newsapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;
import newsday.zhuoxing.com.newsapp.R;
import newsday.zhuoxing.com.newsapp.activity.AddActivity;
import newsday.zhuoxing.com.newsapp.adapter.NewTabPagerAdapter;
import newsday.zhuoxing.com.newsapp.util.SharedPreUtil;

/**
 * Created by Administrator on 2016/9/1.
 */
public class NewsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private NewTabPagerAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> tabTitleList;
    private SharedPreUtil sharedPreUtil;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        sharedPreUtil = new SharedPreUtil(getActivity());
        return inflater.inflate(R.layout.activity_fragment_news, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout_fragment_news);
        viewPager = (ViewPager) view.findViewById(R.id.ViewPager_news);
        imageView = (ImageView) view.findViewById(R.id.iv_add);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivityForResult(intent, 100);
            }
        });
        addFragment();
    }

    private void addFragment() {
        fragmentList = new ArrayList<>();
        if (tabTitleList != null)
            tabTitleList.clear();
        fragmentList.clear();
        tabTitleList = sharedPreUtil.getFromSharedInfoList();
        for (int i = 0; i < tabTitleList.size(); i++) {
            //页卡标题信息传递到fragment中
            BaiDuNewsFragment temp = new BaiDuNewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("newName",tabTitleList.get(i));
            temp.setArguments(bundle);
            fragmentList.add(temp);
        }
        adapter = new NewTabPagerAdapter(getActivity().getSupportFragmentManager(), fragmentList, tabTitleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101 && requestCode == 100 && data.getBooleanExtra("needUpdate", false)) {
            addFragment();
        }
    }

}
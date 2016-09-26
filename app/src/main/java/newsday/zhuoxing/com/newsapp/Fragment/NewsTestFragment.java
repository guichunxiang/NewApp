package newsday.zhuoxing.com.newsapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import newsday.zhuoxing.com.newsapp.R;

/**
 * Created by Administrator on 2016/9/1.
 */
public class NewsTestFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment_news_test,container,false);
    }
}

package newsday.zhuoxing.com.newsapp.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import newsday.zhuoxing.com.newsapp.R;
import newsday.zhuoxing.com.newsapp.util.NotificationUtil;

/**
 * Created by Administrator on 2016/9/13.
 */
public class SettingActivity extends Activity {
    private ToggleButton toggleButton;
    private SharedPreferences sharedPreferences;
    private Toolbar toolbar;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findView();
    }

    private void findView(){
        toggleButton = (ToggleButton)findViewById(R.id.setting_tb);
        sharedPreferences = this.getSharedPreferences("isCheck",1);
       imageView = (ImageView)findViewById(R.id.iv_set_back);
        final boolean b = sharedPreferences.getBoolean("isCheck",false);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(b){
                    NotificationUtil.OpenNotufication(SettingActivity.this);
                }
                else {
                    NotificationUtil.CloseNotification(SettingActivity.this);
                }
            }
        });

    }
}

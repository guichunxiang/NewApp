package newsday.zhuoxing.com.newsapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import newsday.zhuoxing.com.newsapp.R;

/**
 * 侧滑菜单登录
 * Created by Administrator on 2016/9/13.
 */
public class LoadingThreeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imageView;
    private TextView textView, textView2;
    private EditText editTextName, editTextPassword;
    private TextInputLayout textInputLayoutName, textInputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_loading_item);
        findView();
        setOnClick();
    }

    /**
     * 找控件
     */
    private void findView() {
        toolbar = (Toolbar) findViewById(R.id.loading_toolbar);
        imageView = (ImageView) findViewById(R.id.iv_loading_back);
        textView = (TextView) findViewById(R.id.tv_loading);
        textView2 = (TextView) findViewById(R.id.tv_userOther);
        editTextName = (EditText) findViewById(R.id.editText_Name);
        editTextPassword = (EditText) findViewById(R.id.editText_password);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.TextInputName);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.TextInput_password);
        textInputLayoutPassword.setErrorEnabled(true);
    }

    /**
     * 监听
     */
    private void setOnClick() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextName.getText().equals("") || editTextPassword.getText().equals("")) {
                    View view1 = getCurrentFocus();
                    if (view1 == null) {
                        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    Toast.makeText(LoadingThreeActivity.this, "输入为空", Toast.LENGTH_SHORT).show();
                } else {
                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    Toast.makeText(LoadingThreeActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoadingThreeActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

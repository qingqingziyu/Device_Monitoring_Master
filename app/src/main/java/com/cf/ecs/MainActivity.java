package com.cf.ecs;

import android.os.Bundle;

import com.cf.ecs.base.BaseActivity;
import com.cf.ecs.base.IView;
import com.cf.ecs.http.Api;
import com.cf.ecs.http.JsonResponseCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import androidx.core.content.ContextCompat;

public class MainActivity extends BaseActivity implements IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        LoadBusiness();
    }

    @Override
    public void initData() {

    }

    @Override
    public void LoadBusiness() {
        OkGo.<String>get(Api.ServiceUrl)
                .execute(new JsonResponseCallback<String>() {
                    @Override
                    public void onSuccess(String corpno) {

                    }
                });
    }
}

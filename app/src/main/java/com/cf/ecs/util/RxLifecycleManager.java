/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cf.ecs.util;


import android.app.Activity;


import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;

/**
 * ================================================
 * 使用此类操作 RxLifecycle 的特性
 * 用来管理生命周期
 *
 * @author 陈飞
 * ================================================
 */

public class RxLifecycleManager implements LifecycleObserver {

//    public Activity activity;
//
//    public Fragment fragment;
//
//    public RxLifecycleManager(Activity activity) {
//        this.activity = activity;
//    }
//
//    public RxLifecycleManager(Fragment fragment) {
//        this.fragment = fragment;
//    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {

    }

}

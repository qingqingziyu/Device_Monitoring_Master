package com.cf.ecs.http;

import com.cf.ecs.CFApp;
import com.cf.ecs.base.ILoad;
import com.cf.ecs.util.LogUtils;
import com.cf.ecs.util.TipDialogUtils;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.exception.HttpException;
import com.lzy.okgo.exception.StorageException;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @Params:自定义解析json
 * @开发者:陈飞
 * @日期:2019/3/12 20:52
 **/
public abstract class JsonResponseCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;

    private ILoad iLoad;

    public JsonResponseCallback() {

    }


    public JsonResponseCallback(Type type) {


        this.type = type;
    }

    public JsonResponseCallback(Class<T> clazz) {


        this.clazz = clazz;
    }

    /**
     * @Params:带进度条
     * @开发者:陈飞
     * @日期:2019/3/20 15:32
     **/
    public JsonResponseCallback(ILoad iLoad) {
        this.iLoad = iLoad;
    }

    /**
     * @Params:带进度条
     * @开发者:陈飞
     * @日期:2019/3/20 15:32
     **/
    public JsonResponseCallback(Type type, ILoad iLoad) {

        this.type = type;
        this.iLoad = iLoad;
    }

    /**
     * @Params:带进度条
     * @开发者:陈飞
     * @日期:2019/3/20 15:32
     **/
    public JsonResponseCallback(Class<T> clazz, ILoad iLoad) {

        this.clazz = clazz;
        this.iLoad = iLoad;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        if (iLoad != null) {
            iLoad.showLoad();
        }
    }

    @Override
    public void onFinish() {
        super.onFinish();
        if (iLoad != null) {
            iLoad.closeLoad();
        }
    }

    @Override
    public T convertResponse(Response response) {

        ResponseBody responseBody = response.body();
        //如果为空则
        if (responseBody == null) {
            return null;
        }

        T data = null;

        //打印http


        JsonReader jsonReader = new JsonReader(responseBody.charStream());

        if (type != null) {
            data = CFApp.getGson().fromJson(jsonReader, type);
        } else if (clazz != null) {
            data = CFApp.getGson().fromJson(jsonReader, clazz);
        } else {
            //获取父类型
            Type genType = getClass().getGenericSuperclass();
            //获取bean类型
            Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            //解析
            data = CFApp.getGson().fromJson(jsonReader, type);
        }


        return data;
    }

    @Override
    public void onSuccess(com.lzy.okgo.model.Response<T> response) {
        onSuccess(response.body());
    }


    public abstract void onSuccess(T t);

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        //网络异常使用
        Throwable exception = response.getException();
        //直接报异常
        if (exception != null) {
            exception.printStackTrace();
        }

        if (exception instanceof UnknownHostException || exception instanceof ConnectException) {
            TipDialogUtils.showFailDialog(ContextHolder.getContext(), "网络连接失败，请连接网络");
        } else if (exception instanceof SocketTimeoutException) {
            TipDialogUtils.showFailDialog(ContextHolder.getContext(), "网络请求超时");
        } else if (exception instanceof HttpException) {
            TipDialogUtils.showFailDialog(ContextHolder.getContext(), "服务端响应码404和500了，知道该什么办吗？");
        } else if (exception instanceof StorageException) {
            TipDialogUtils.showFailDialog(ContextHolder.getContext(), "SD卡不存在或者没有权限");
        } else if (exception instanceof IllegalStateException) {
            LogUtils.debugInfo("http", exception.getMessage());
        }
    }
}

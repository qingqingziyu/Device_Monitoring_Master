package com.cf.ecs.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @Params:对话框基类
 * @开发者:陈飞
 * @日期:2019/3/22 16:20
 **/
public class BaseDialog extends Dialog implements ILoad {

    private LoadingShowDialog loadingDialog;

    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * @param msg 加载框提示语
     *            显示圆形加载进度对话框
     */
    protected void showLoadingDialog(String msg) {
        if (loadingDialog == null) {
            loadingDialog = LoadingShowDialog.createDialog(getContext());
            loadingDialog.setCancelable(false);
            loadingDialog.setMessage(msg);
        }
        loadingDialog.show();
    }

    /**
     * 显示圆形加载进度对话框
     */
    protected void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = LoadingShowDialog.createDialog(getContext());
            loadingDialog.setCancelable(false);
            loadingDialog.setMessage("正在加载..");
        }
        loadingDialog.show();
    }

    /**
     * 关闭进度对话框
     */
    protected void closeLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    @Override
    public void showLoad() {
        showLoadingDialog();
    }

    @Override
    public void closeLoad() {
        closeLoadingDialog();
    }
}

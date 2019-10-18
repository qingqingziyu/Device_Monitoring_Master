package com.cf.ecs.util;

import android.content.Context;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

/**
 * @Params:提示性对话框工具类
 * @开发者:陈飞
 * @日期:2019/3/12 19:40
 **/
public class TipDialogUtils {

    private static QMUITipDialog qmuiTipDialog;

    /**
     * @Params:加载对话框
     * @开发者:陈飞
     * @日期:2019/3/12 19:41
     **/
    public static synchronized void showLoadingDialog(Context context, String content) {
        qmuiTipDialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(content)
                .create();
        //显示
        qmuiTipDialog.show();

        qmuiTipDialog.getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                qmuiTipDialog.dismiss();
            }
        },1500);
    }


    /**
     * @Params:提示成功，正确的，积极的对话框对话框
     * @开发者:陈飞
     * @日期:2019/3/12 19:41
     **/
    public static synchronized void showSuccessDialog(Context context, String content) {
        qmuiTipDialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                .setTipWord(content)
                .create();
        //显示
        qmuiTipDialog.show();

        qmuiTipDialog.getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                qmuiTipDialog.dismiss();
            }
        },1500);
    }


    /**
     * @Params:提示失败的，消极的对话框对话框
     * @开发者:陈飞
     * @日期:2019/3/12 19:41
     **/
    public static synchronized void showFailDialog(Context context, String content) {
        qmuiTipDialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord(content)
                .create();
        //显示
        qmuiTipDialog.show();

        qmuiTipDialog.getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                qmuiTipDialog.dismiss();
            }
        },1500);
    }

    /**
     * @Params:提示信息的对话框
     * @开发者:陈飞
     * @日期:2019/3/12 19:41
     **/
    public static synchronized void showInfoDialog(Context context, String content) {
        qmuiTipDialog = new QMUITipDialog.Builder(context)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_INFO)
                .setTipWord(content)
                .create();
        //显示
        qmuiTipDialog.show();

        qmuiTipDialog.getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                qmuiTipDialog.dismiss();
            }
        },1500);
    }

    /**
     * @Params:提示带图标的对话框
     * @开发者:陈飞
     * @日期:2019/3/12 19:41
     **/
    public static synchronized void showNoneImageDialog(Context context, String content) {
        qmuiTipDialog = new QMUITipDialog.Builder(context)
                .setTipWord(content)
                .create();
        //显示
        qmuiTipDialog.show();

        qmuiTipDialog.getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                qmuiTipDialog.dismiss();
            }
        },1500);
    }


    /**
     * @Params:自定义提示对话框
     * @开发者:陈飞
     * @日期:2019/3/12 19:41
     **/
    public static synchronized void showCustomDialog(Context context, @LayoutRes int layout) {
        qmuiTipDialog = new QMUITipDialog.CustomBuilder(context)
                .setContent(layout)
                .create();
        //显示
        qmuiTipDialog.show();

        qmuiTipDialog.getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                qmuiTipDialog.dismiss();
            }
        },1500);
    }

    /**
     * @Params:关闭
     * @开发者:陈飞
     * @日期:2019/3/12 19:56
     **/
    public static void dismiss() {
        if (qmuiTipDialog != null) {
            qmuiTipDialog.dismiss();
        }
    }

}

package com.cf.ecs.base;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.cf.ecs.R;


/**
 * 与服务器交互加载提示框
 *
 */
public class LoadingShowDialog extends Dialog {

    private Context context = null;
    private static LoadingShowDialog customProgressDialog = null;

    public LoadingShowDialog(Context context) {
        super(context);
        this.context = context;
    }

    public LoadingShowDialog(Context context, int theme) {
        super(context, theme);
    }

    public static LoadingShowDialog createDialog(Context context) {
        customProgressDialog = new LoadingShowDialog(context, R.style.CustomProgressDialog);
        customProgressDialog.setContentView(R.layout.customer_loading_dialog);
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;

        return customProgressDialog;
    }

    public void onWindowFocusChanged(boolean hasFocus) {

        if (customProgressDialog == null) {
            return;
        }
//        ImageView imageView = (ImageView) customProgressDialog
//                .findViewById(R.id.loadingImageView);
//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView
//                .getBackground();
//        animationDrawable.start();
    }

    /**
     * [Summary] setTitile 标题
     * @param strTitle
     * @return
     */
    public LoadingShowDialog setTitile(String strTitle) {
        return customProgressDialog;
    }

    /**
     * [Summary] setMessage 提示内容
     * @param strMessage
     * @return
     */
    public LoadingShowDialog setMessage(String strMessage) {
        TextView tvMsg = (TextView) customProgressDialog.findViewById(R.id.tips_loading_msg);
        if (tvMsg != null) {
            tvMsg.setText(strMessage);
        }
        return customProgressDialog;
    }
}


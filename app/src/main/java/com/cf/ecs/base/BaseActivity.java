package com.cf.ecs.base;

import android.content.Intent;
import android.os.Bundle;

import android.view.MotionEvent;

import com.cf.ecs.R;
import com.cf.ecs.http.ContextHolder;
import com.cf.ecs.util.RxLifecycleManager;
import com.cf.ecs.util.TipDialogUtils;
import com.qmuiteam.qmui.arch.QMUIActivity;
import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import lombok.Setter;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
/**
 * @Params:Activity基类
 * @开发者:陈飞
 * @日期:2019/5/8 09:59
 **/
public class BaseActivity extends QMUIActivity implements ISupportActivity,ILoad {
    final SupportActivityDelegate mDelegate = new SupportActivityDelegate(this);
    private RxLifecycleManager rxLifecycleManager;
    private LoadingShowDialog loadingDialog;

    protected void setTestBarColor(int colorId){
        QMUIStatusBarHelper.translucent(this, colorId);
    }

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return mDelegate;
    }

    /**
     * Perform some extra transactions.
     * 额外的事务：自定义Tag，添加SharedElement动画，操作非回退栈Fragment
     */
    @Override
    public ExtraTransaction extraTransaction() {
        return mDelegate.extraTransaction();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextHolder.initial(this);
        mDelegate.onCreate(savedInstanceState);
        //添加生命周期监听
        rxLifecycleManager = new RxLifecycleManager();
        getLifecycle().addObserver(rxLifecycleManager);


        setTestBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ContextHolder.initial(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDelegate.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
    }

    /**
     * Note： return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    /**
     * 不建议复写该方法,请使用 {@link #onBackPressedSupport} 代替
     */
//    @Override
//    final public void onBackPressed() {
//        mDelegate.onBackPressed();
//    }

    /**
     * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
     * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
     */
    @Override
    public void onBackPressedSupport() {
        mDelegate.onBackPressedSupport();
    }

    /**
     * 获取设置的全局动画 copy
     *
     * @return FragmentAnimator
     */
    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mDelegate.getFragmentAnimator();
    }

    /**
     * Set all fragments animation.
     * 设置Fragment内的全局动画
     */
    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     * <p/>
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mDelegate.onCreateFragmentAnimator();
    }

    /**
     * Causes the Runnable r to be added to the action queue.
     * <p>
     * The runnable will be run after all the previous action has been run.
     * <p>
     * 前面的事务全部执行后 执行该Action
     */
    @Override
    public void post(Runnable runnable) {
        mDelegate.post(runnable);
    }

    /****************************************以下为可选方法(Optional methods)******************************************************/

    // 选择性拓展其他方法
    public void loadRootFragment(int containerId, @NonNull ISupportFragment toFragment) {
        mDelegate.loadRootFragment(containerId, toFragment);
    }

    public void start(ISupportFragment toFragment) {
        mDelegate.start(toFragment);
    }

    /**
     * @param launchMode Same as Activity's LaunchMode.
     */
    public void start(ISupportFragment toFragment, @ISupportFragment.LaunchMode int launchMode) {
        mDelegate.start(toFragment, launchMode);
    }

    /**
     * It is recommended to use {(ISupportFragment, Class, boolean)}.
     *
     * @see #popTo(Class, boolean)
     * +
     * @see #start(ISupportFragment)
     */
    public void startWithPopTo(ISupportFragment toFragment, Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment);
    }

    /**
     * Pop the fragment.
     */
    public void pop() {
        mDelegate.pop();
    }

    /**
     * Pop the last fragment transition from the manager's fragment
     * back stack.
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment);
    }

    /**
     * If you want to begin another FragmentTransaction immediately after popTo(), use this method.
     * 如果你想在出栈后, 立刻进行FragmentTransaction操作，请使用该方法
     */
    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable);
    }

    public void popTo(Class<?> targetFragmentClass, boolean includeTargetFragment, Runnable afterPopTransactionRunnable, int popAnim) {
        mDelegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable, popAnim);
    }

    /**
     * 得到位于栈顶Fragment
     */
    public ISupportFragment getTopFragment() {
        return SupportHelper.getTopFragment(getSupportFragmentManager());
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }

    /**
     * @param msg 加载框提示语
     *            显示圆形加载进度对话框
     */
    protected void showLoadingDialog(String msg) {
        if (loadingDialog == null) {
            loadingDialog = LoadingShowDialog.createDialog(this);
            loadingDialog.setCancelable(false);
            loadingDialog.setMessage(msg);
        }
        loadingDialog.show();
    }

    /**
     * 显示圆形加载进度对话框
     */
    protected synchronized void  showLoadingDialog() {
        showLoadingDialog("正在加载");
    }

    /**
     * 关闭进度对话框
     */
    protected synchronized void closeLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    /**
     * 用来回调返回事件
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (onActivityResultListener != null) {
            onActivityResultListener.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Setter
    private onActivityResultListener onActivityResultListener;

    @Override
    public synchronized void showLoad() {
        showLoadingDialog();
    }

    @Override
    public synchronized void closeLoad() {
        closeLoadingDialog();
    }

    /**
     * @Params:接口回调器
     * @开发者:陈飞
     * @日期:2019/5/8 10:00
     **/
    public interface onActivityResultListener {
        void onActivityResult(int requestCode, int resultCode, @Nullable Intent data);
    }


}

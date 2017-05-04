package com.future.wk.newcontacter.activity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

import com.future.wk.newcontacter.mvp.view.LocalContacterFragment;
import com.future.wk.newcontacter.R;
import com.future.wk.newcontacter.base.Log;
import com.future.wk.newcontacter.base.activity.BaseActivity;
import com.future.wk.newcontacter.base.mvp.presenter.BasePresenter;
import com.future.wk.newcontacter.base.view.TabStripView;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    private static String TAG = "MainActivity";

    @Bind(R.id.navigateTabBar)
    TabStripView navigateTabBar;

    public static void startMainActivity(Activity activity) {
        Log.d(TAG,"startMainActivity");
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }
    @Override
    protected boolean isEnableStatusBar() {
        return true;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        Log.d(TAG,"onInitView start");
        //EventBus.getDefault().register(this);
        //对应xml中的containerId
        navigateTabBar.setFrameLayoutId(R.id.main_container);
        //对应xml中的navigateTabTextColor
        navigateTabBar.setTabTextColor(getResources().getColor(R.color.gray_font_3));
        //对应xml中的navigateTabSelectedTextColor
        navigateTabBar.setSelectedTabTextColor(getResources().getColor(R.color.colorPrimary));

        //恢复选项状态
        navigateTabBar.onRestoreInstanceState(savedInstanceState);

        navigateTabBar.addTab(LocalContacterFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_local_normal, R.mipmap.ic_tab_local_pressed, "通讯录"));
        navigateTabBar.addTab(LocalContacterFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_network_normal, R.mipmap.ic_tab_network_pressed, "微服务"));
        navigateTabBar.addTab(LocalContacterFragment.class, new TabStripView.TabParam(R.mipmap.ic_tab_setting_normal, R.mipmap.ic_tab_setting_pressed, "设置"));

        Log.d(TAG,"onInitView end");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前选中的选项状态
        navigateTabBar.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        Log.d(TAG,"onResume");
        super.onResume();
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;
    private void exitBy2Click() {
        Timer tExit;
        if (!isExit) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

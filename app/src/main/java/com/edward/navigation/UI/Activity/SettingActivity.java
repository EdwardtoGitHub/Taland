package com.edward.navigation.UI.Activity;

import android.view.View;

import com.edward.navigation.Model.cache.UserCache;
import com.edward.navigation.R;
import com.edward.navigation.UI.Base.BaseActivity;
import com.edward.navigation.UI.Base.BasePresenter;
import com.edward.navigation.app.AppConst;
import com.edward.navigation.app.MyApp;
import com.edward.navigation.widget.CustomDialog;
import com.lqr.optionitemview.OptionItemView;

import butterknife.BindView;
import io.rong.imlib.RongIMClient;

/**
 * @创建者 CSDN_LQR
 * @描述 设置界面
 */
public class SettingActivity extends BaseActivity {

    private View mExitView;

    @BindView(R.id.oivAbout)
    OptionItemView mOivAbout;
    @BindView(R.id.oivHelpFeedback)
    OptionItemView mOivHelpFeedback;
    @BindView(R.id.oivExit)
    OptionItemView mOivExit;
    private CustomDialog mExitDialog;

    @Override
    public void initListener() {
        mOivAbout.setOnClickListener(v -> jumpToActivity(AboutActivity.class));
        mOivHelpFeedback.setOnClickListener(v1 -> jumpToWebViewActivity(AppConst.WeChatUrl.HELP_FEED_BACK));
        mOivExit.setOnClickListener(v -> {
            if (mExitView == null) {
                mExitView = View.inflate(this, R.layout.dialog_exit, null);
                mExitDialog = new CustomDialog(this, mExitView, R.style.MyDialog);
                mExitView.findViewById(R.id.tvExitAccount).setOnClickListener(v1 -> {
                    RongIMClient.getInstance().logout();
                    UserCache.clear();
                    mExitDialog.dismiss();
                    MyApp.exit();
                    jumpToActivityAndClearTask(LoginActivity.class);
                });
                mExitView.findViewById(R.id.tvExitApp).setOnClickListener(v1 -> {
                    RongIMClient.getInstance().disconnect();
                    mExitDialog.dismiss();
                    MyApp.exit();
                });
            }
            mExitDialog.show();
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_setting;
    }
}

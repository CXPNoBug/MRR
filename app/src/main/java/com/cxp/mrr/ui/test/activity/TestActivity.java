package com.cxp.mrr.ui.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cxp.mrr.R;
import com.cxp.mrr.base.BaseActivity;

import butterknife.OnClick;

/**
 * 文 件 名: TestActivity
 * 创 建 人: CXP
 * 创建日期: 2017-01-21 15:01
 * 描    述: 示例
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class TestActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @OnClick({R.id.bt_rx, R.id.bt_user,R.id.bt_permissions,R.id.bt_logger})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_rx:
                startActivity(RxActivity.class);
                break;
            case R.id.bt_user:
                startActivity(UserActivity.class);
                break;
            case R.id.bt_permissions:
                startActivity(PermissionsActivity.class);
                break;
            case R.id.bt_logger:
                startActivity(LoggerActivity.class);
                break;
        }
    }
}

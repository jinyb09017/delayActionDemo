package com.goodluck.abbott;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.toptechs.libaction.action.CallUnit;

/**
 * get discount
 */

public class DiscountActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_LOGIN = 1000;

    public static void startActivityForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, DiscountActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void start(Activity activity) {
        Intent intent = new Intent(activity, DiscountActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_activivty);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserConfigCache.setDiscount(DiscountActivity.this, true);

                //这里执行延迟的action方法。
                CallUnit.reCall();

                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

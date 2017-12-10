package com.goodluck.abbott;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OrderDetailActivity extends AppCompatActivity {
    private static final String EXTRA_ORDER_ID = "orderId";

    private TextView mOrderInfoText;
    private String mOrderId;




    @Override
    protected void onStart() {
        super.onStart();

    }

    public static void startActivity(Context context, String orderId) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_ORDER_ID, orderId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        mOrderId = getIntent().getStringExtra(EXTRA_ORDER_ID);
        mOrderInfoText = (TextView) findViewById(R.id.orderInfo);
    }



}

# Simple Android Interceptor
It's a simple interceptor implementation in Android and it workes with activity's lifecycle.
As you known, in our App a some activity's content info is retrieved from server with account ID, so you need login first, after login successfully then go into that activity, it's not very difficult to implement everyone, but a lot of boring code work.
# how to use?
For example:  LoginInterceptor  
1. define interceptor class:  
```java  
public class LoginInterceptor extends Interceptor {

 @Override
 public int getRequestCode() {
     return LoginActivity.REQUEST_CODE_LOGIN;
 }

 @Override
 public boolean isSatisfied(Context context) {
     return UserConfigCache.isLogin(context);
 }

 @Override
 public void process(Activity activity) {
     LoginActivity.startActivityForResult(activity, getRequestCode());
 }
}
```
2. use LoginInterceptor in activity:  
```java
@InterceptWith(LoginInterceptor.class)
public class OrderDetailActivity extends InterceptorActivity {
    private static final String EXTRA_ORDER_ID = "orderId";

    private TextView mOrderInfoText;
    private String mOrderId;

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

    @Override
    protected void invoked() {
        super.invoked();
        mOrderInfoText.setText("订单信息(order id: " + mOrderId + ")");
        // 根据orderId请求完整的订单信息
    }
}
```

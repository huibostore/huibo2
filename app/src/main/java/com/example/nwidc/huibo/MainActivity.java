//
//                                  _oo8oo_
//                                 o8888888o
//                                 88" . "88
//                                 (| -_- |)
//                                 0\  =  /0
//                               ___/'==='\___
//                             .' \\|     |// '.
//                            / \\|||  :  |||// \
//                           / _||||| -:- |||||_ \
//                          |   | \\\  -  /// |   |
//                          | \_|  ''\---/''  |_/ |
//                          \  .-\__  '-'  __/-.  /
//                        ___'. .'  /--.--\  '. .'___
//                     ."" '<  '.___\_<|>_/___.'  >' "".
//                    | | :  `- \`.:`\ _ /`:.`/ -`  : | |
//                    \  \ `-.   \_ __\ /__ _/   .-` /  /
//                =====`-.____`.___ \_____/ ___.`____.-`=====
//                                  `=---=`
//
//
//               ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//                          佛祖保佑
//
package com.example.nwidc.huibo;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nwidc.huibo.fragment.BookingFragment;
import com.example.nwidc.huibo.fragment.ChartFragment;
import com.example.nwidc.huibo.fragment.CloudpayFragment;
import com.example.nwidc.huibo.fragment.CollectFragment;
import com.example.nwidc.huibo.fragment.HomeMenuFragment;
import com.example.nwidc.huibo.fragment.IdleFragment;
import com.example.nwidc.huibo.fragment.MemberFragment;
import com.example.nwidc.huibo.fragment.NearbyFragment;
import com.example.nwidc.huibo.fragment.PurchaseFragment;
import com.example.nwidc.huibo.fragment.Sign_Fragment;
import com.example.nwidc.huibo.fragment.SnakeFragment;
import com.example.nwidc.huibo.fragment.SortFragment;
import com.example.nwidc.huibo.fragment.TakeFragment;
import com.example.nwidc.huibo.fragment.WholesaleFragment;
import com.zaaach.citypicker.CityPickerActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private long exitTime = 0;

    //UI Object
    private LinearLayout txt_channel;
    private LinearLayout txt_message;
    private LinearLayout txt_better;
    private LinearLayout txt_setting;
    private LinearLayout txt_settings;
    private FrameLayout ly_content;

    //Fragment Object
    private HomeMenuFragment fg1;
    private ChartFragment fg3;
    private MemberFragment fg4;
    private Sign_Fragment fg45;
    private SortFragment fg5;
    private CollectFragment fg2;
    private PurchaseFragment PurchaseFragment;
    private CloudpayFragment cludepay;
    private Context mContext;
    private String Login;
    private PurchaseFragment fg51;
    private BookingFragment fg52;
    private CloudpayFragment fg53;
    private TakeFragment fg54;
    private WholesaleFragment fg55;
    private IdleFragment fg56;
    private NearbyFragment fg57;
    private SnakeFragment fg58;

    private FragmentManager fManager;
    private SharedHelper sh;
    private String result = "";
    private static final int REQUEST_CODE_PICK_CITY = 233;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        fManager = getSupportFragmentManager();
        mContext = getApplicationContext();
        sh = new SharedHelper(mContext);
        bindViews();

        //判断是否为跳转购物车
        Intent intent = getIntent();
        String name = intent.getStringExtra("GoCart");


        txt_channel.performClick();   //模拟一次点击，既进去后选择第一项
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        if("Cart".equals(name)){
            txt_better.performClick();
        }


    }



    //UI组件初始化与事件绑定
    private void bindViews() {
        txt_channel = (LinearLayout) findViewById(R.id.txt_channel);
        txt_message = (LinearLayout) findViewById(R.id.txt_message);
        txt_better = (LinearLayout) findViewById(R.id.txt_better);
        txt_setting = (LinearLayout) findViewById(R.id.txt_setting);
        txt_settings = (LinearLayout) findViewById(R.id.txt_settings);
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        txt_channel.setOnClickListener(this);
        txt_message.setOnClickListener(this);
        txt_better.setOnClickListener(this);
        txt_setting.setOnClickListener(this);
        txt_settings.setOnClickListener(this);
    }

    //重置所有文本的选中状态
    private void setSelected(){
        txt_channel.setSelected(false);
        txt_message.setSelected(false);
        txt_better.setSelected(false);
        txt_setting.setSelected(false);
        txt_settings.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
        if(fg5 != null)fragmentTransaction.hide(fg5);
        if(fg45 != null)fragmentTransaction.hide(fg45);
        if(fg51 != null)fragmentTransaction.hide(fg51);
        if(fg52 != null)fragmentTransaction.hide(fg52);
        if(fg53 != null)fragmentTransaction.hide(fg53);
        if(fg54 != null)fragmentTransaction.hide(fg54);
        if(fg55 != null)fragmentTransaction.hide(fg55);
        if(fg56 != null)fragmentTransaction.hide(fg56);
        if(fg57 != null)fragmentTransaction.hide(fg57);
        if(fg58 != null)fragmentTransaction.hide(fg5);

        if(PurchaseFragment != null)fragmentTransaction.hide(PurchaseFragment);
    }

    //城市切换

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                TextView a= (TextView) getSupportFragmentManager().findFragmentById(R.id.ly_content).getView().findViewById(R.id.city);
                a.setText(city);
                //保存选择
                sh.savecity(city);
            }
        }
    }

    //搜索
    public void onClickSearch(View v){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    //抢购

    public void onClickgoods_info(View v){

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, Goods_infoActivity.class);
        startActivity(intent);
    }


    //抢购

    public void onClickSnap(View v){

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, BookingActivity.class);
        startActivity(intent);
    }

    //团购
    public void onClicktuangou(View v) {

        fg51 = new PurchaseFragment();
        fManager.beginTransaction()
                .addToBackStack(null)
                .add(R.id.ly_content, fg51)
                .commit();



    }
    //预售
    public void onClickBooking(View v) {

        fg52 = new BookingFragment();
        fManager.beginTransaction()
                .hide(fg1)
                .addToBackStack(null)
                .add(R.id.ly_content, fg52)
                .commit();

    }
    //云购
    public void onClickCloudpay(View v){

        fg53 = new CloudpayFragment();

        fManager.beginTransaction()
                .hide(fg1)
                .addToBackStack(null)
                .add(R.id.ly_content,fg53)
                .commit();

    }
    //外卖到家
    public void onClickTake(View v){

        fg54 = new TakeFragment();

        fManager.beginTransaction()
                .hide(fg1)
                .addToBackStack(null)
                .add(R.id.ly_content,fg54)
                .commit();

    }

    //批发

    public void onclickWholesale(View v){

        fg55 = new WholesaleFragment();

        fManager.beginTransaction()
                .hide(fg1)
                .addToBackStack(null)
                .add(R.id.ly_content,fg55)
                .commit();

    }

    //二手

    public void onClickIdle(View v){

        fg56 = new IdleFragment();

        fManager.beginTransaction()
                .hide(fg1)
                .addToBackStack(null)
                .add(R.id.ly_content,fg56)
                .commit();

    }

    //附近

    public void onClickNearby(View v){

        fg57 = new NearbyFragment();
        fManager.beginTransaction()
                .hide(fg1)
                .addToBackStack(null)
                .add(R.id.ly_content,fg57)
                .commit();

    }

    //摇一摇

    public void onClickShake(View v){

        fg58 = new SnakeFragment();
        fManager.beginTransaction()
                .hide(fg1)
                .addToBackStack(null)
                .add(R.id.ly_content,fg58)
                .commit();


    }

    //降价

//    public void onClickprice(View v){
//
//
//
//    }

    public void onClickprice(View v){


        Intent intent = new Intent();
        intent.setClass(MainActivity.this, TestActivity.class);
        startActivity(intent);


    }


    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.txt_channel:
                setSelected();
                txt_channel.setSelected(true);
                if(fg1 == null){
                    fg1 = new HomeMenuFragment();

                    fTransaction.add(R.id.ly_content,fg1);

                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.txt_message:
                setSelected();
                txt_message.setSelected(true);
                if(fg2 == null){
                    fg2 = new CollectFragment();
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.txt_better:
                setSelected();
                txt_better.setSelected(true);
                if(fg3 == null){
                    fg3 = new ChartFragment();
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
            case R.id.txt_setting:
                setSelected();
                txt_setting.setSelected(true);
                if(fg4 == null){
                    result = LoginChenge.getInstance().getLoginInfo();
                    if(result == "null"){
                        fg45 = new Sign_Fragment();
                        fTransaction.add(R.id.ly_content,fg45);
                    }else{
                        fg4 = new MemberFragment();
                        fTransaction.add(R.id.ly_content,fg4);
                    }

                }else{
                    fTransaction.show(fg4);
                }
                break;
            case R.id.txt_settings:
                setSelected();
                txt_settings.setSelected(true);
                if(fg5 == null){
                    fg5 = new SortFragment();

                    fTransaction.add(R.id.ly_content,fg5);
                }else{
                    fTransaction.show(fg5);
                }
                break;
        }
        fTransaction.commit();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //城市
    public void onClickcity(View v) {
        startActivityForResult(new Intent(MainActivity.this, CityPickerActivity.class), REQUEST_CODE_PICK_CITY);

    }





}
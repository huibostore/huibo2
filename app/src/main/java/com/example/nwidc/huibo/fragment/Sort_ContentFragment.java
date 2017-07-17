package com.example.nwidc.huibo.fragment;
    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v4.app.FragmentTransaction;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.FrameLayout;
    import android.widget.LinearLayout;
    import android.widget.TextView;
    import com.example.nwidc.huibo.R;

/**
 * Created by janiszhang on 2016/6/6.
 */

public class Sort_ContentFragment extends Fragment implements View.OnClickListener{

    //UI Object
    private LinearLayout huibo_sort0;
    private LinearLayout huibo_sort1;
    private LinearLayout huibo_sort2;
    private LinearLayout huibo_sort3;
    private LinearLayout huibo_sort4;
    private LinearLayout huibo_sort5;
    private LinearLayout huibo_sort6;
    private LinearLayout huibo_sort7;
    private LinearLayout huibo_sort8;
    private LinearLayout huibo_sort9;
    private LinearLayout huibo_sort10;
    private LinearLayout huibo_sort11;
    private LinearLayout huibo_sort12;
    private View viewContent;

    private FragmentManager fManagers;
    //Fragment

    private Sort_ListFragment sort_fg0;
    private ErrorFragment sort_fg1;
    private ErrorFragment sort_fg2;
    private ErrorFragment sort_fg3;
    private ErrorFragment sort_fg4;
    private ErrorFragment sort_fg5;
    private ErrorFragment sort_fg6;
    private ErrorFragment sort_fg7;
    private ErrorFragment sort_fg8;
    private ErrorFragment sort_fg9;
    private ErrorFragment sort_fg10;
    private ErrorFragment sort_fg11;
    private ErrorFragment sort_fg12;

    // @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //布局文件中只有一个居中的TextView
        viewContent = inflater.inflate(R.layout.activity_sort,container,false);
        fManagers = getChildFragmentManager();
        bindViews();
        huibo_sort0.performClick();//模拟点击


        return viewContent;
    }

    //UI组件初始化与事件绑定
    private void bindViews() {
        huibo_sort0 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort0);
        huibo_sort1 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort1);
        huibo_sort2 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort2);
        huibo_sort3 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort3);
        huibo_sort4 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort4);
        huibo_sort5 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort5);
        huibo_sort6 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort6);
        huibo_sort7 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort7);
        huibo_sort8 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort8);
        huibo_sort9 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort9);
        huibo_sort10 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort10);
        huibo_sort11 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort11);
        huibo_sort12 = (LinearLayout) viewContent.findViewById(R.id.huibo_sort12);


        huibo_sort0.setOnClickListener(this);
        huibo_sort1.setOnClickListener(this);
        huibo_sort2.setOnClickListener(this);
        huibo_sort3.setOnClickListener(this);
        huibo_sort4.setOnClickListener(this);
        huibo_sort5.setOnClickListener(this);
        huibo_sort6.setOnClickListener(this);
        huibo_sort7.setOnClickListener(this);
        huibo_sort8.setOnClickListener(this);
        huibo_sort9.setOnClickListener(this);
        huibo_sort10.setOnClickListener(this);
        huibo_sort11.setOnClickListener(this);
        huibo_sort12.setOnClickListener(this);

    }

    //重置所有文本的选中状态
    private void setSelected(){
        huibo_sort0.setSelected(false);
        huibo_sort1.setSelected(false);
        huibo_sort2.setSelected(false);
        huibo_sort3.setSelected(false);
        huibo_sort4.setSelected(false);
        huibo_sort5.setSelected(false);
        huibo_sort6.setSelected(false);
        huibo_sort7.setSelected(false);
        huibo_sort8.setSelected(false);
        huibo_sort9.setSelected(false);
        huibo_sort10.setSelected(false);
        huibo_sort11.setSelected(false);
        huibo_sort12.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideSort_Fragment(FragmentTransaction fragmentTransaction){
        if(sort_fg0 != null)fragmentTransaction.hide(sort_fg0);
        if(sort_fg1 != null)fragmentTransaction.hide(sort_fg1);
        if(sort_fg2 != null)fragmentTransaction.hide(sort_fg2);
        if(sort_fg3 != null)fragmentTransaction.hide(sort_fg3);
        if(sort_fg4 != null)fragmentTransaction.hide(sort_fg4);
        if(sort_fg5 != null)fragmentTransaction.hide(sort_fg5);
        if(sort_fg6 != null)fragmentTransaction.hide(sort_fg6);
        if(sort_fg7 != null)fragmentTransaction.hide(sort_fg7);
        if(sort_fg8 != null)fragmentTransaction.hide(sort_fg8);
        if(sort_fg9 != null)fragmentTransaction.hide(sort_fg9);
        if(sort_fg10 != null)fragmentTransaction.hide(sort_fg10);
        if(sort_fg11 != null)fragmentTransaction.hide(sort_fg11);
        if(sort_fg12 != null)fragmentTransaction.hide(sort_fg12);


    }

    public void onClick(View v) {
        FragmentTransaction fTransactions = fManagers.beginTransaction();
        hideSort_Fragment(fTransactions);
        switch (v.getId()){
            case R.id.huibo_sort0:
                setSelected();
                huibo_sort0.setSelected(true);
                if(sort_fg0 == null){
                    sort_fg0 = new Sort_ListFragment();
                    fTransactions.add(R.id.ly_content,sort_fg0);
                }else{
                    fTransactions.show(sort_fg0);
                }
                break;
            case R.id.huibo_sort1:
                setSelected();
                huibo_sort1.setSelected(true);
                if(sort_fg1 == null){
                    sort_fg1 = new ErrorFragment();
                    fTransactions.add(R.id.ly_content,sort_fg1);
                }else{
                    fTransactions.show(sort_fg1);
                }
                break;
            case R.id.huibo_sort2:
                setSelected();
                huibo_sort2.setSelected(true);
                if(sort_fg2 == null){
                    sort_fg2 = new ErrorFragment();
                    fTransactions.add(R.id.ly_content,sort_fg2);
                }else{
                    fTransactions.show(sort_fg2);
                }
                break;
            case R.id.huibo_sort3:
                setSelected();
                huibo_sort3.setSelected(true);
                if(sort_fg3 == null){
                    sort_fg3 = new ErrorFragment();
                    fTransactions.add(R.id.ly_content,sort_fg3);
                }else{
                    fTransactions.show(sort_fg3);
                }
                break;
            case R.id.huibo_sort4:
                setSelected();
                huibo_sort4.setSelected(true);
                if(sort_fg4 == null){
                    sort_fg4 = new ErrorFragment();
                    fTransactions.add(R.id.ly_content,sort_fg4);
                }else{
                    fTransactions.show(sort_fg4);
                }
                break;
            case R.id.huibo_sort5:
                setSelected();
                huibo_sort5.setSelected(true);
                if(sort_fg5 == null){
                    sort_fg5 = new ErrorFragment();
                    fTransactions.add(R.id.ly_content,sort_fg5);
                }else{
                    fTransactions.show(sort_fg5);
                }
                break;
            case R.id.huibo_sort6:
                setSelected();
                huibo_sort6.setSelected(true);
                if(sort_fg6 == null){
                    sort_fg6 = new ErrorFragment();
                    fTransactions.add(R.id.ly_content,sort_fg6);
                }else{
                    fTransactions.show(sort_fg6);
                }
                break;
            case R.id.huibo_sort7:
                setSelected();
                huibo_sort7.setSelected(true);
                if(sort_fg7 == null){
                    sort_fg7 = new ErrorFragment();
                    fTransactions.add(R.id.ly_content,sort_fg7);
                }else{
                    fTransactions.show(sort_fg7);
                }
                break;
            case R.id.huibo_sort8:
                setSelected();
                huibo_sort8.setSelected(true);
                if(sort_fg8 == null){
                    sort_fg8 = new ErrorFragment();
                    fTransactions.add(R.id.ly_content,sort_fg8);
                }else{
                    fTransactions.show(sort_fg8);
                }
                break;
            case R.id.huibo_sort9:
                setSelected();
                huibo_sort9.setSelected(true);
                if(sort_fg9 == null){
                    sort_fg9 = new ErrorFragment();
                    fTransactions.add(R.id.ly_content,sort_fg9);
                }else{
                    fTransactions.show(sort_fg9);
                }
                break;
            case R.id.huibo_sort10:
                setSelected();
                huibo_sort10.setSelected(true);
                if(sort_fg10 == null){
                    sort_fg10 = new ErrorFragment();
                    fTransactions.add(R.id.ly_content,sort_fg10);
                }else{
                    fTransactions.show(sort_fg10);
                }
                break;
            case R.id.huibo_sort11:
                setSelected();
                huibo_sort11.setSelected(true);
                if(sort_fg11 == null){
                    sort_fg11 = new ErrorFragment();
                    fTransactions.add(R.id.ly_content,sort_fg11);
                }else{
                    fTransactions.show(sort_fg11);
                }
                break;
            case R.id.huibo_sort12:
                setSelected();
                huibo_sort12.setSelected(true);
                if(sort_fg12 == null){
                    sort_fg12 = new ErrorFragment();
                    fTransactions.add(R.id.ly_content,sort_fg12);
                }else{
                    fTransactions.show(sort_fg12);
                }
                break;
        }
        fTransactions.commit();
    }


}

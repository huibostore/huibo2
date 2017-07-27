package com.example.nwidc.huibo.fragment;
    import android.os.Bundle;
    import android.support.annotation.Nullable;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v4.app.FragmentTransaction;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.FrameLayout;
    import android.widget.GridView;
    import android.widget.LinearLayout;
    import android.widget.ListView;
    import android.widget.TextView;

    import com.example.nwidc.huibo.Adapter.MyListViewAdapter1;
    import com.example.nwidc.huibo.Adapter.MyListViewAdapter2;
    import com.example.nwidc.huibo.Person;
    import com.example.nwidc.huibo.R;
    import com.example.nwidc.huibo.Util.Config;
    import com.example.nwidc.huibo.Util.ToastUtils;
    import com.google.gson.Gson;
    import com.google.gson.reflect.TypeToken;

    import java.util.ArrayList;
    import java.util.List;

    import cn.alien95.resthttp.BuildConfig;
    import cn.alien95.resthttp.request.RestHttp;
    import cn.alien95.resthttp.request.callback.HttpCallback;
    import cn.alien95.resthttp.request.http.HttpRequest;

/**
 * Created by janiszhang on 2016/6/6.
 */

public class Sort_ContentFragment extends Fragment {


    private View viewContent;

    private int selectIndex=0;

    private ArrayList mMenus = null;


    private String[] strs1={"常用分类1","常用分类2","常用分类3","常用分类4","常用分类5","常用分类6","常用分类7","常用分类8","常用分类9","常用分类10"};
    private String[] strs2={"服饰内衣1","服饰内衣2","服饰内衣3","服饰内衣4","服饰内衣5","服饰内衣6","服饰内衣7","服饰内衣8","服饰内衣9","服饰内衣10","服饰内衣11","服饰内衣12","服饰内衣13","服饰内衣14","服饰内衣15","服饰内衣16"};
    private String[] strs3={"鞋靴1","鞋靴2","鞋靴3","鞋靴4","鞋靴5","鞋靴6"};
    private String[] strs4={"手机1","手机2","手机3","手机4"};
    private String[] strs5={"家用电器1","家用电器2","家用电器3","家用电器4","家用电器5","家用电器6","家用电器7","家用电器8"};
    private String[][] allData={strs1,strs2,strs3,strs4,strs5,strs1,strs2,strs3,strs4,strs5,strs1,strs2,strs3,strs4,strs5};
    private ListView mListView1;
    private GridView mListView2;
    private MyListViewAdapter1 adapter1;
    private MyListViewAdapter2 adapter2;

    private  String GET_URL = Config.Category_URL;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //布局文件中只有一个居中的TextView
        viewContent = inflater.inflate(R.layout.activity_sort,container,false);
        //restHttp
        RestHttp.initialize(getActivity());
        RestHttp.setDiskCacheSize(100 * 1024 * 1024);
        if (BuildConfig.DEBUG) {
            RestHttp.setDebug(true, "network");
        }
        get();


        return viewContent;
    }
    private void initView() {
        mListView1= (ListView) viewContent.findViewById(R.id.list_item_1);
        mListView2= (GridView) viewContent.findViewById(R.id.list_item_2);

        adapter1=new MyListViewAdapter1(mMenus,getActivity(),selectIndex);
        adapter2=new MyListViewAdapter2(allData,getActivity(),selectIndex);
        mListView1.setAdapter(adapter1);
        mListView2.setAdapter(adapter2);

        mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectIndex=position;
                //把下标传过去，然后刷新adapter
                adapter1.setIndex(position);
                adapter1.notifyDataSetChanged();
                //当点击某个item的时候让这个item自动滑动到listview的顶部(下面item够多，如果点击的是最后一个就不能到达顶部了)
                mListView1.smoothScrollToPositionFromTop(position,0);

                adapter2.setIndex(position);
                mListView2.setAdapter(adapter2);
            }
        });

        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(getActivity(),allData[selectIndex][position]);
            }
        });
    }


    /*
    * http get()请求；
    *
    * */

    public void get(){

        HttpRequest.getInstance().get(GET_URL, new HttpCallback() {

            //返回http页面信息 info
            @Override
            public void success(String info) {
                //搜索列表listview
                get_category(info);
            }
        });
    }

    public void get_category(String info){

        Gson gson = new Gson();

        ArrayList<cc> ps = gson.fromJson(info,new TypeToken<List<cc>>(){}.getType());




        mMenus = new ArrayList();
        for(int i =0; i<ps.size();i++){

            mMenus.add(ps.get(i));



        }






//        mMenus = (String[])al.toArray(new String[al.size()]);

        System.out.println(mMenus);

        initView();

    }

    class cc{
        public String gc_name;
        public void setGc_name(String gc_name){
            this.gc_name = gc_name;
        }

        @Override
        public String toString() {
            return this.gc_name;
        }

    }

}



package com.example.nwidc.huibo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nwidc.huibo.R;
import com.example.nwidc.huibo.View.Person;

import java.util.List;

public class PersonAdapter extends BaseAdapter {
    private List<Person> data;
    private static Context context;
    private static int resoureId;

    PersonAdapter(List<Person> data, Context context, int resoureId){
        this.context = context;
        this.data = data;
        this.resoureId = resoureId;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return  data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        Person person = data.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resoureId,null);//找到lv布局
            viewHolder = new ViewHolder(convertView);//找到布局下面的组件并缓存起来
            convertView.setTag(viewHolder);//缓存组件对象

        }else{
            viewHolder = (ViewHolder) convertView.getTag();//获取组件对象
        }
        //组件对象填充数据
        viewHolder.name.setText(person.getName());
        viewHolder.age.setText("年纪："+person.getAge()+"");
        viewHolder.address.setText("家庭住址："+person.getAddress());
        viewHolder.no.setText("学号："+person.getNo());
        viewHolder.teacher.setText("班主任："+person.getTeacher());
        return convertView;
    }

    public  class ViewHolder{
        private TextView name;
        private TextView age;
        private TextView address;
        private TextView no;
        private TextView teacher;
        ViewHolder(View contentView){
//            this.name = (TextView) contentView.findViewById(R.id.name);
//            this.age = (TextView) contentView.findViewById(R.id.age);
//            this.address = (TextView) contentView.findViewById(R.id.address);
//            this.no = (TextView) contentView.findViewById(R.id.no);
//            this.teacher = (TextView) contentView.findViewById(R.id.teacher);
        }
    }
}

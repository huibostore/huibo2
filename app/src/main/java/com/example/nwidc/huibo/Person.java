package com.example.nwidc.huibo;

/**
 * Created by hello on 2017/6/7.
 */

public class Person {
    public String goods_id;
    public String goods_name;
    public String goods_price;
    public void setGoods_id(String goods_id){
        this.goods_id = goods_id;
    }
    public String getGoods_id(){
        return this.goods_id;
    }
    public void setGoods_name(String goods_name){
        this.goods_name = goods_name;
    }
    public String getGoods_name(){
        return this.goods_name;
    }

    public String getGoods_price(){
        return this.goods_price;
    }
    public void setGoods_price(String goods_price){
        this.goods_price = goods_price;
    }

    @Override
    public String toString() {
        return this.goods_name  + this.goods_price + this.goods_id;
    }

}

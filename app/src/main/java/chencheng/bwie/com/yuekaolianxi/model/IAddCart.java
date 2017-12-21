package chencheng.bwie.com.yuekaolianxi.model;

import java.util.Map;

import chencheng.bwie.com.yuekaolianxi.bean.GoodBean;
import chencheng.bwie.com.yuekaolianxi.net.OnNetListener;

/**
 * Created by dell on 2017/12/19.
 */

public interface IAddCart {
    public void showAdd(Map<String,String> params, OnNetListener<GoodBean> onNetListener);
}

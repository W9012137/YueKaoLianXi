package chencheng.bwie.com.yuekaolianxi.model;

import java.util.Map;

import chencheng.bwie.com.yuekaolianxi.bean.GoWuBean;
import chencheng.bwie.com.yuekaolianxi.net.OnNetListener;

/**
 * Created by dell on 2017/12/20.
 */

public interface GouWuMoel {
    public void showG(Map<String,String> params, OnNetListener<GoWuBean> onNetListener);
}

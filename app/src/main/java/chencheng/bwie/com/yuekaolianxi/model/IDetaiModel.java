package chencheng.bwie.com.yuekaolianxi.model;

import java.util.Map;

import chencheng.bwie.com.yuekaolianxi.bean.Bean;
import chencheng.bwie.com.yuekaolianxi.net.OnNetListener;

/**
 * Created by dell on 2017/12/19.
 */

public interface IDetaiModel {
    public void ShowDetril(Map<String,String> params, OnNetListener<Bean> onNetListener);

}

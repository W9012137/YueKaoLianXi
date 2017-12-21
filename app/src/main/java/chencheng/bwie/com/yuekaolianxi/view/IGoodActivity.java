package chencheng.bwie.com.yuekaolianxi.view;

import java.util.List;

import chencheng.bwie.com.yuekaolianxi.bean.GoWuBean;

/**
 * Created by dell on 2017/12/20.
 */

public interface IGoodActivity {
    public void ShowGo(List<GoWuBean.DataBean> group,List<List<GoWuBean.DataBean.ListBean>> child);
}

package chencheng.bwie.com.yuekaolianxi.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chencheng.bwie.com.yuekaolianxi.bean.GoWuBean;
import chencheng.bwie.com.yuekaolianxi.model.GouWuMoel;
import chencheng.bwie.com.yuekaolianxi.model.Gowu;
import chencheng.bwie.com.yuekaolianxi.net.OnNetListener;
import chencheng.bwie.com.yuekaolianxi.view.IGoodActivity;

/**
 * Created by dell on 2017/12/20.
 */

public class GoWuPresenter {
    private IGoodActivity iGoodActivity;
    private final GouWuMoel gouWuMoel;

    public GoWuPresenter(IGoodActivity iGoodActivity) {
        this.iGoodActivity = iGoodActivity;
        gouWuMoel = new Gowu();
    }
    public void getGowu(){
        Map<String,String> params=new HashMap<>();
        params.put("uid", "1234");
        params.put("pid","71");
        gouWuMoel.showG(params, new OnNetListener<GoWuBean>() {
            @Override
            public void onSuccess(GoWuBean goWuBean) {
                final List<GoWuBean.DataBean> group = goWuBean.getData();
                List<List<GoWuBean.DataBean.ListBean>> child=new ArrayList<List<GoWuBean.DataBean.ListBean>>();
                for (int i=0;i<group.size();i++){
                    child.add(group.get(i).getList());
                }
                iGoodActivity.ShowGo(group,child);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}

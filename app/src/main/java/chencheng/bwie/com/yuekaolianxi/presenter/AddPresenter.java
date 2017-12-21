package chencheng.bwie.com.yuekaolianxi.presenter;

import java.util.HashMap;
import java.util.Map;

import chencheng.bwie.com.yuekaolianxi.bean.GoodBean;
import chencheng.bwie.com.yuekaolianxi.model.AddCart;
import chencheng.bwie.com.yuekaolianxi.model.IAddCart;
import chencheng.bwie.com.yuekaolianxi.net.OnNetListener;
import chencheng.bwie.com.yuekaolianxi.view.XiangQingActivity;

/**
 * Created by dell on 2017/12/19.
 */

public class AddPresenter {
    private XiangQingActivity xiangQingActivity;
    private final IAddCart iAddCart;

    public AddPresenter(XiangQingActivity xiangQingActivity){
        this.xiangQingActivity=xiangQingActivity;
        iAddCart = new AddCart();
    }
    public void showAdd(){
        Map<String,String> params=new HashMap<>();
        params.put("pid","71");
       params.put("uid","39");

        iAddCart.showAdd(params, new OnNetListener<GoodBean>() {
            @Override
            public void onSuccess(GoodBean goodBean) {
                if (xiangQingActivity != null) {
                    xiangQingActivity.show(goodBean.getCode().equals("0") ? "添加成功了" : "添加失败了");
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}

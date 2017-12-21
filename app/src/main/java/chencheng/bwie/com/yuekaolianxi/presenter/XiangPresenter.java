package chencheng.bwie.com.yuekaolianxi.presenter;

import java.util.HashMap;
import java.util.Map;

import chencheng.bwie.com.yuekaolianxi.bean.Bean;
import chencheng.bwie.com.yuekaolianxi.model.DetailModel;
import chencheng.bwie.com.yuekaolianxi.model.IDetaiModel;
import chencheng.bwie.com.yuekaolianxi.net.OnNetListener;
import chencheng.bwie.com.yuekaolianxi.view.XiangQingActivity;

/**
 * Created by dell on 2017/12/19.
 */

public class XiangPresenter {
    private XiangQingActivity xiangQingActivity;
    private final IDetaiModel iDetaiModel;

    public XiangPresenter(XiangQingActivity xiangQingActivity){
        this.xiangQingActivity=xiangQingActivity;
        iDetaiModel = new DetailModel();
    }
    public void showDa(){
        Map<String, String> params = new HashMap<>();
        params.put("pid", "71");
      iDetaiModel.ShowDetril(params, new OnNetListener<Bean>() {
          @Override
          public void onSuccess(Bean bean) {
              xiangQingActivity.showX(bean);
          }

          @Override
          public void onFailure(Exception e) {

          }
      });


    }
}

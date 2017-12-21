package chencheng.bwie.com.yuekaolianxi.model;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import chencheng.bwie.com.yuekaolianxi.bean.GoWuBean;
import chencheng.bwie.com.yuekaolianxi.net.OkHttpUtils;
import chencheng.bwie.com.yuekaolianxi.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by dell on 2017/12/20.
 */

public class Gowu implements GouWuMoel {
Handler handler=new Handler(Looper.getMainLooper());
    @Override
    public void showG(Map<String, String> params, final OnNetListener<GoWuBean> onNetListener) {
        OkHttpUtils.getOkHttpUtils().doPost("https://www.zhaoapi.cn/product/getCarts", params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
           onNetListener.onFailure(e);
                Log.i("ATG","失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
             String str=response.body().string();
                final GoWuBean goWuBean = new Gson().fromJson(str, GoWuBean.class);
               handler.post(new Runnable() {
                   @Override
                   public void run() {
                   onNetListener.onSuccess(goWuBean);
                       Log.i("ATG",""+goWuBean);
               }
               });
            }
        });
    }
}

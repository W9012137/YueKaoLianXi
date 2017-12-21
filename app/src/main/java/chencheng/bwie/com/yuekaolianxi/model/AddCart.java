package chencheng.bwie.com.yuekaolianxi.model;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import chencheng.bwie.com.yuekaolianxi.bean.GoodBean;
import chencheng.bwie.com.yuekaolianxi.net.OkHttpUtils;
import chencheng.bwie.com.yuekaolianxi.net.OnNetListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by dell on 2017/12/19.
 */

public class AddCart implements IAddCart {
    Handler handler=new Handler(Looper.getMainLooper());
    @Override
    public void showAdd(Map<String, String> params, final OnNetListener<GoodBean> onNetListener) {
        OkHttpUtils.getOkHttpUtils().doPost("https://www.zhaoapi.cn/product/getProductDetail", params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str=response.body().string();
                final GoodBean goodBean = new Gson().fromJson(str, GoodBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(goodBean);
                    }
                });
            }
        });

    }
}

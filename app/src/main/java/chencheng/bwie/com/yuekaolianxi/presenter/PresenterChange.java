package chencheng.bwie.com.yuekaolianxi.presenter;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import chencheng.bwie.com.yuekaolianxi.Base;
import chencheng.bwie.com.yuekaolianxi.model.ModuleChange;

/**
 * Created by dell on 2017/12/21.
 */

public class PresenterChange {
    private ModuleChange moduleChange = new ModuleChange();
    PresenterChangeListener presenterChangeListener;

    public PresenterChange(PresenterChangeListener presenterChangeListener) {
        this.presenterChangeListener = presenterChangeListener;
    }

    private Handler handler = new Handler(Looper.getMainLooper());
    public void getData(String status,String id){
        moduleChange.getData(status, id, new ModuleChange.ModuleChangeListener() {
            @Override
            public void success(final String s) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Base dataChangeBean = new Gson().fromJson(s, Base.class);
                        if(presenterChangeListener !=null){

                            presenterChangeListener.mySuccess(dataChangeBean);

                        }
                    }
                });

            }
        });
    }
    public interface PresenterChangeListener{
        void mySuccess(Base dataChangeBean);
    }
}

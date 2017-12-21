package chencheng.bwie.com.yuekaolianxi.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import chencheng.bwie.com.yuekaolianxi.R;

public class MainActivity extends AppCompatActivity {
private ProgressBarView pbv;
    private int progress=120;
    private int time=3;
   Handler handler=new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           time--;
           if (time==0){
               startActivity(new Intent(MainActivity.this,XianQingActivity.class));
               finish();
           }else {
               progress+=120;
               pbv.setProgress(progress);
               handler.sendEmptyMessageDelayed(0,1000);
           }
       }
   };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView= (ImageView) findViewById(R.id.imageView);
        pbv= (ProgressBarView) findViewById(R.id.my_progess);
        setAnimation(imageView);
         handler.sendEmptyMessage(0);
        pbv.setProgress(progress);

    }
    //执行动画方法
    private void setAnimation(ImageView imageView){
        //应用图标从屏幕最上方平移到屏幕中间
        ObjectAnimator trans=ObjectAnimator.ofFloat(imageView,"translationY",0f,500f).setDuration(1000);
        //缩放由2倍到1倍
        ObjectAnimator scalX=ObjectAnimator.ofFloat(imageView,"scaleX",2f,1f).setDuration(1000);
        ObjectAnimator scalY=ObjectAnimator.ofFloat(imageView,"scaleY",2f,1f).setDuration(1000);
        //渐变从完全透明到完全不透明
        ObjectAnimator alpha=ObjectAnimator.ofFloat(imageView,"alpha",0.0f,1f).setDuration(1000);
        // 旋转为旋转一圈
        ObjectAnimator rotate=ObjectAnimator.ofFloat(imageView,"rotation",0f,360f).setDuration(1000);
        //组合
        AnimatorSet setAnimaton=new AnimatorSet();
        setAnimaton.play(trans).before(scalX).before(scalY).before(alpha).before(rotate);
        setAnimaton.start();;
    }
}

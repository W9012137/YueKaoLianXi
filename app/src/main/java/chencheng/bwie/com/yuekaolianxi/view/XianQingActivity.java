package chencheng.bwie.com.yuekaolianxi.view;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import chencheng.bwie.com.yuekaolianxi.bean.Bean;
import chencheng.bwie.com.yuekaolianxi.R;
import chencheng.bwie.com.yuekaolianxi.presenter.AddPresenter;
import chencheng.bwie.com.yuekaolianxi.presenter.XiangPresenter;

/**
 * Created by dell on 2017/12/19.
 */

public class XianQingActivity extends AppCompatActivity implements XiangQingActivity, View.OnClickListener {
    private ImageView mImageView2;
    private RelativeLayout mRelative01;
    private ImageView mIv;
    /**
     * 年后
     */
    private TextView mTitle;
    /**
     * 年后
     */
    private TextView mYuanjia;
    /**
     * 年后
     */
    private TextView mPrice;
    /**
     * 购物车
     */
    private Button mBut1;
    /**
     * 加入购物车
     */
    private Button mBut2;
    XiangPresenter xiangPresenter;
   AddPresenter addPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xianqing);
        xiangPresenter = new XiangPresenter(this);
        addPresenter=new AddPresenter(this);
        xiangPresenter.showDa();
        initView();
    }

    @Override
    public void showX(Bean data) {
        //设置图片 下标为0的图片数据
        String images = data.getData().getImages();
        String[] split = images.split("\\|");
        //ImageLoader加载图片数组中的图片
        ImageLoader.getInstance().displayImage(split[0], mIv);
        mTitle.setText(data.getData().getTitle());
        mYuanjia.setText("原价：￥" + data.getData().getPrice());
        mYuanjia.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mPrice.setText("优惠价:" + data.getData().getBargainPrice());

    }

    @Override
    public void show(String str) {
        Toast.makeText(XianQingActivity.this, str, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mImageView2 = (ImageView) findViewById(R.id.imageView2);
        mRelative01 = (RelativeLayout) findViewById(R.id.relative01);
        mIv = (ImageView) findViewById(R.id.iv);
        mTitle = (TextView) findViewById(R.id.title);
        mYuanjia = (TextView) findViewById(R.id.yuanjia);
        mPrice = (TextView) findViewById(R.id.price);
        mBut1 = (Button) findViewById(R.id.but1);
        mBut1.setOnClickListener(this);
        mBut2 = (Button) findViewById(R.id.but2);
        mBut2.setOnClickListener(this);
        mImageView2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but1:
                startActivity(new Intent(XianQingActivity.this,GoodActivity.class));
                break;
            case R.id.but2:
                addPresenter.showAdd();
                break;
            case R.id.imageView2:
                startActivity(new Intent(XianQingActivity.this,MainActivity.class));

                break;
        }
    }
}

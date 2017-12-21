package chencheng.bwie.com.yuekaolianxi.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import chencheng.bwie.com.yuekaolianxi.MyAdapter;
import chencheng.bwie.com.yuekaolianxi.R;
import chencheng.bwie.com.yuekaolianxi.bean.BaseBean;
import chencheng.bwie.com.yuekaolianxi.bean.GoWuBean;
import chencheng.bwie.com.yuekaolianxi.presenter.GoWuPresenter;

/**
 * Created by dell on 2017/12/19.
 */

public class GoodActivity extends AppCompatActivity implements View.OnClickListener, IGoodActivity {
    private ImageView mIv;
    /**
     * 全选/反选
     */
    private CheckBox mCheck;
    /**
     * 总价：
     */
    private Button mPrice;
    private ExpandableListView mExview;
    /**
     * 全选/反选
     */
    private TextView mTextView;
    GoWuPresenter presenter;
    MyAdapter myAdapter;
    BaseBean priceAndCount;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodwu);
        presenter = new GoWuPresenter(this);
       presenter.getGowu();
        initView();
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mIv.setOnClickListener(this);
        mCheck = (CheckBox) findViewById(R.id.check);
        mCheck.setOnClickListener(this);
        mExview = (ExpandableListView) findViewById(R.id.exview);
        mTextView = (TextView) findViewById(R.id.textView);
        mPrice = (Button) findViewById(R.id.price);
        mPrice.setOnClickListener(this);
        mCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myAdapter.AllOrNone(mCheck.isChecked());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv:
                startActivity(new Intent(GoodActivity.this, XianQingActivity.class));
                break;
            case R.id.price:
                final Intent intent = new Intent(GoodActivity.this, Other.class);
                intent.putExtra("money",priceAndCount.getPrice()+"");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void ShowGo(List<GoWuBean.DataBean> group, List<List<GoWuBean.DataBean.ListBean>> child) {
     myAdapter=new MyAdapter(this,group,child);
        mExview.setGroupIndicator(null);
        mExview.setAdapter(myAdapter);
        for (int i=0;i<group.size();i++){
            mExview.expandGroup(i);
        }
    }
 public void setPriceAndCount(BaseBean priceAndCount){
     this.priceAndCount=priceAndCount;
   mPrice.setText("合计："+priceAndCount.getPrice());
 };



    public void setChild(boolean flag) {
        mCheck.setChecked(flag);
    }
}

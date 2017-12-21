package chencheng.bwie.com.yuekaolianxi.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import chencheng.bwie.com.yuekaolianxi.R;

/**
 * Created by dell on 2017/12/21.
 */

public class Other extends AppCompatActivity implements View.OnClickListener {
    /**
     * 你好
     */
    private TextView mPrice;
    /**
     * 立即下单
     */
    private TextView mBut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liebiao);
        Intent it=getIntent();
        String money=it.getStringExtra("money");
        initView();
        mPrice.setText("实付款："+money);
    }

    private void initView() {
        mPrice = (TextView) findViewById(R.id.price);
        mBut = (TextView) findViewById(R.id.but);
        mBut.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but:
                startActivity(new Intent(Other.this,LieBiao.class));
                break;
        }
    }
}

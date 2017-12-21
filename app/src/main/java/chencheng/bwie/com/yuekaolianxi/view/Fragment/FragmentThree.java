package chencheng.bwie.com.yuekaolianxi.view.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liaoinstan.springview.widget.SpringView;

import java.util.List;

import chencheng.bwie.com.yuekaolianxi.Adapater;
import chencheng.bwie.com.yuekaolianxi.R;
import chencheng.bwie.com.yuekaolianxi.bean.DataDataBean;
import chencheng.bwie.com.yuekaolianxi.presenter.MyPresenterOther;

/**
 * Created by dell on 2017/12/21.
 */

public class FragmentThree extends Fragment implements MyPresenterOther.PresenterListerenOther {
    private RecyclerView recyclerView;
    private SpringView springView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item3,container,false);
        recyclerView = view.findViewById(R.id.recyclerView01);
        springView = view.findViewById(R.id.springView);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final MyPresenterOther myPresenter = new MyPresenterOther();
        myPresenter.getData("71","1","1",this);
    }

    @Override
    public void success(List<DataDataBean.DataBean> data) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Adapater myAdapter = new Adapater(getActivity(),data);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void failed() {

    }
}

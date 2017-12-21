package chencheng.bwie.com.yuekaolianxi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import chencheng.bwie.com.yuekaolianxi.bean.BaseBean;
import chencheng.bwie.com.yuekaolianxi.bean.GoWuBean;
import chencheng.bwie.com.yuekaolianxi.view.GoodActivity;

/**
 * Created by dell on 2017/12/20.
 */

public class MyAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<GoWuBean.DataBean> group;
    private List<List<GoWuBean.DataBean.ListBean>> child;

    public MyAdapter(Context context, List<GoWuBean.DataBean> group, List<List<GoWuBean.DataBean.ListBean>> child) {
        this.context = context;
        this.group = group;
        this.child = child;
    }


    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return child.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return group.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return child.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {

        final GroupViewHolder holder;
        if (view==null){
            holder=new GroupViewHolder();
            view=View.inflate(context,R.layout.group,null);
            holder.cbgroup=view.findViewById(R.id.group);
            holder.name=view.findViewById(R.id.name);
            view.setTag(holder);
        }else{
            holder= (GroupViewHolder) view.getTag();
        }
        final GoWuBean.DataBean dataBean = group.get(i);
        holder.name.setText(dataBean.getSellerName());
        holder.cbgroup.setChecked(dataBean.ischeckd());

        //点击全选
        holder.cbgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //一级列表isChecked的值
                dataBean.setIscheckd(holder.cbgroup.isChecked());
                //二级列表isChecked的值
                setChild(i,holder.cbgroup.isChecked());
                ((GoodActivity) context).setChild(isAllGroup());
                //价钱
                setPriceAndCount();
                //刷新
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        final ChildViewHolder holder;
        if (view==null){
            view=View.inflate(context,R.layout.child,null);
            holder=new ChildViewHolder();
            holder.iv=view.findViewById(R.id.iv);
            holder.title=view.findViewById(R.id.title);
            holder.price=view.findViewById(R.id.price);
            holder.cbchild=view.findViewById(R.id.child);
            holder.iv_add=view.findViewById(R.id.iv_add);
            holder.iv_del=view.findViewById(R.id.iv_del);
            holder.remove=view.findViewById(R.id.removr);
            holder.num=view.findViewById(R.id.num);
            view.setTag(holder);
        }else{
            holder= (ChildViewHolder) view.getTag();
        }
        final GoWuBean.DataBean.ListBean listBean = child.get(i).get(i1);
        final String images = listBean.getImages();
        Glide.with(context).load(images.split("\\|")[0]).into(holder.iv);
        holder.title.setText(listBean.getTitle());
        holder.cbchild.setChecked(listBean.ischeckd());
        holder.price.setText(listBean.getPrice()+"");
        holder.num.setText(listBean.getCount()+"");
        holder.cbchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1.二级列表的checkbox状态值
                listBean.setIscheckd(holder.cbchild.isChecked());
                //2.一级列表的checkbox状态值
                group.get(i).setIscheckd(isAllChild(i));
                setPriceAndCount();
                notifyDataSetChanged();
            }
        });
        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=listBean.getCount();
                count++;
                listBean.setCount(count);
                notifyDataSetChanged();
            }
        });
        holder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count=listBean.getCount();

                if (count<=1){
                    count=1;
                }else{
                    count--;
                }
                listBean.setCount(count);
                setPriceAndCount();
                notifyDataSetChanged();
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final List<GoWuBean.DataBean.ListBean> listBeen = child.get(i);
                if (listBeen.size()>0){
                    listBeen.remove(i1);
                }if (listBeen.size()==0){
                    child.remove(i);
                    group.remove(i);
                }
                setPriceAndCount();
                ((GoodActivity) context).setChild(isAllGroup());
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
    class GroupViewHolder{
        CheckBox cbgroup;
        TextView name;

    }

    class  ChildViewHolder {
    CheckBox cbchild;
        TextView title,price,num,remove;
        ImageView iv,iv_del,iv_add;
    }
    //设置一级列表对应的二级列表checkbox状态
    private void setChild(int postion,boolean flag){
        final List<GoWuBean.DataBean.ListBean> listBeen = child.get(postion);
        for (int i=0;i<listBeen.size();i++){
            listBeen.get(i).setIscheckd(flag);
        }
    }
    //判断一级列表是否选中
    private boolean isAllGroup(){
        if (group.size()==0){
            return false;
        }
        for (int i=0;i<group.size();i++){
            if(!group.get(i).ischeckd()){
                return  false;
            }
        }
        return  true;
    }
    //判断二级列表是否选中
    private  boolean isAllChild(int position ){
        final List<GoWuBean.DataBean.ListBean> listBeen = child.get(position);
        for (int i=0;i<listBeen.size();i++){
            if(!listBeen.get(i).ischeckd()){
                return  false;
            }
        }
        return true;
    }
    private void setPriceAndCount() {
        ((GoodActivity) context).setPriceAndCount(compute());
    }
    private BaseBean compute(){
        double price = 0;
        int count = 0;
        for (int i = 0; i < group.size(); i++) {
            final List<GoWuBean.DataBean.ListBean> listBeen = child.get(i);
            for (int j = 0; j < listBeen.size(); j++) {
                if (listBeen.get(j).ischeckd()) {
                    price += listBeen.get(j).getPrice() * listBeen.get(j).getCount();
                    count += listBeen.get(j).getCount();
                }
            }
        }
    return new BaseBean(price,count);
    }
    /**
     * 全选或者全不选
     *
     *
     */
    public void AllOrNone(boolean flag) {
        for (int i = 0; i < group.size(); i++) {
            group.get(i).setIscheckd(flag);
           setChild(i,flag);
        }
        setPriceAndCount();
        notifyDataSetChanged();
    }
}

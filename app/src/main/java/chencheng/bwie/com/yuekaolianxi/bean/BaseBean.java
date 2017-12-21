package chencheng.bwie.com.yuekaolianxi.bean;

/**
 * Created by dell on 2017/12/21.
 */

public class BaseBean {
    private double price;
    private int count;

    public BaseBean(double price, int count) {
        this.price = price;
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

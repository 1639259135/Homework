package bookstore.order.domain;

import java.util.Map;

public class Order {

    private String oid;
    private String uid;
    private String orderTime;
    private double price;
    private int state;
    private String address;

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" + "oid='" + oid + '\''+ "uid='" + uid + '\'' + ", orderTime='" + orderTime + '\'' + ", price='" + price + '\'' + ", state='" + state + '\'' + ", address='" + address + '\'' + '}';
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

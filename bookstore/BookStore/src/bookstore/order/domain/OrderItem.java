package bookstore.order.domain;

public class OrderItem {

    private String iid;
    private int count;
    private double subtotal;
    private String bid;
    private String oid;

    public OrderItem() {
    }

    @Override
    public String toString() {
        return "OrderItem{" + "iid='" + iid + '\'' + ", count=" + count + ", subtotal=" + subtotal + ", bid='" + bid + '\'' + ", oid='" + oid + '\'' + '}';
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}

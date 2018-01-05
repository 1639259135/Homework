package bean;

public class Book {
    private int bid;
    private String bookname;
    private String author;
    private String price;
    private String cover;

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" + "bid=" + bid + ", bookname='" + bookname + '\'' + ", author='" + author + '\'' + ", price='" + price + '\'' + '}';
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}

package bookstore.cart.domain;

import bookstore.book.domain.Book;

import java.util.Map;

public class Cart {

    private Map<String,CartItem > cartMap;

    public Cart() {
    }

    @Override
    public String toString() {
        return "Cart{" + "cartMap=" + cartMap + '}';
    }

    public Map<String, CartItem> getCartMap() {
        return cartMap;
    }

    public void setCartMap(Map<String, CartItem> cartMap) {
        this.cartMap = cartMap;
    }

}

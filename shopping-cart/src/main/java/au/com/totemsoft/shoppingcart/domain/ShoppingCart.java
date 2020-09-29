package au.com.totemsoft.shoppingcart.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@lombok.Data
public class ShoppingCart {

    private final UUID id;

    private final List<ShoppingCartItem> items = new ArrayList<>();

    public ShoppingCart() {
        this.id = UUID.randomUUID();
    }

    public ShoppingCart(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public List<ShoppingCartItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return id.toString();
    }

}

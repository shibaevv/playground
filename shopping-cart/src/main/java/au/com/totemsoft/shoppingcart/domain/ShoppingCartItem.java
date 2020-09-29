package au.com.totemsoft.shoppingcart.domain;

import java.math.BigDecimal;

//@lombok.Data
public class ShoppingCartItem {

    public static final String OPERA_HOUSE = "OH";
    public static final String OPERA_HOUSE_DESC = "Opera house tour";

    public static final String BRIDGE_CLIMB = "BC";
    public static final String BRIDGE_CLIMB_DESC = "Sydney Bridge Climb";

    public static final String SKY_TOWER = "SK";
    public static final String SKY_TOWER_DESC = "Sydney Sky Tower";

    private final String id;

    private final String name;

    private BigDecimal price;

    public ShoppingCartItem(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return id.equals(((ShoppingCartItem) obj).id);
    }

    @Override
    public String toString() {
        return id;
    }

}

package au.com.totemsoft.shoppingcart;

import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.BRIDGE_CLIMB;
import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.BRIDGE_CLIMB_DESC;
import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.OPERA_HOUSE;
import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.OPERA_HOUSE_DESC;
import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.SKY_TOWER;
import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.SKY_TOWER_DESC;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import au.com.totemsoft.shoppingcart.domain.ShoppingCart;
import au.com.totemsoft.shoppingcart.domain.ShoppingCartItem;
import au.com.totemsoft.shoppingcart.exception.ShoppingCartException;
import au.com.totemsoft.shoppingcart.impl.BookingServiceImpl;

public class BookingServiceTest {

    //@Inject
    private BookingService bookingService;

    private ShoppingCart cart;

    @Before
    public void before() throws Exception {
        bookingService = new BookingServiceImpl();
        // create cart (empty)
        cart = bookingService.create();
    }

    @Test
    public void test1() throws ShoppingCartException {
        System.out.println("test1: OH, OH, OH, BC - Total expected: $710.00");
        // OH, OH, OH, BC - Total expected: $710.00
        cart.getItems().add(new ShoppingCartItem(OPERA_HOUSE,  OPERA_HOUSE_DESC,  new BigDecimal("300.00")));
        cart.getItems().add(new ShoppingCartItem(OPERA_HOUSE,  OPERA_HOUSE_DESC,  new BigDecimal("300.00")));
        cart.getItems().add(new ShoppingCartItem(OPERA_HOUSE,  OPERA_HOUSE_DESC,  new BigDecimal("300.00")));
        cart.getItems().add(new ShoppingCartItem(BRIDGE_CLIMB, BRIDGE_CLIMB_DESC, new BigDecimal("110.00")));
        // get total
        BigDecimal total = bookingService.total(cart);
        Assert.assertEquals(new BigDecimal("710.00"), total);
    }

    @Test
    public void test2() throws ShoppingCartException {
        System.out.println("test2: OH, SK - Total expected: $300.00");
        // OH, SK - Total expected: $300.00
        cart.getItems().add(new ShoppingCartItem(OPERA_HOUSE,  OPERA_HOUSE_DESC,  new BigDecimal("300.00")));
        cart.getItems().add(new ShoppingCartItem(SKY_TOWER,    SKY_TOWER_DESC,    new BigDecimal( "30.00")));
        // get total
        BigDecimal total = bookingService.total(cart);
        Assert.assertEquals(new BigDecimal("300.00"), total);
    }

    @Test
    public void test3() throws ShoppingCartException {
        System.out.println("test3: BC, BC, BC, BC, BC, OH - Total expected: $750");
        // BC, BC, BC, BC, BC, OH - Total expected: $750
        cart.getItems().add(new ShoppingCartItem(BRIDGE_CLIMB, BRIDGE_CLIMB_DESC, new BigDecimal("110.00")));
        cart.getItems().add(new ShoppingCartItem(BRIDGE_CLIMB, BRIDGE_CLIMB_DESC, new BigDecimal("110.00")));
        cart.getItems().add(new ShoppingCartItem(BRIDGE_CLIMB, BRIDGE_CLIMB_DESC, new BigDecimal("110.00")));
        cart.getItems().add(new ShoppingCartItem(BRIDGE_CLIMB, BRIDGE_CLIMB_DESC, new BigDecimal("110.00")));
        cart.getItems().add(new ShoppingCartItem(BRIDGE_CLIMB, BRIDGE_CLIMB_DESC, new BigDecimal("110.00")));
        cart.getItems().add(new ShoppingCartItem(OPERA_HOUSE,  OPERA_HOUSE_DESC,  new BigDecimal("300.00")));
        // get total
        BigDecimal total = bookingService.total(cart);
        Assert.assertEquals(new BigDecimal("750.00"), total);
    }

}

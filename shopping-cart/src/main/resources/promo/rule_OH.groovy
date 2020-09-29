import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.BRIDGE_CLIMB;
import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.OPERA_HOUSE;
import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.SKY_TOWER;

import java.math.BigDecimal;

import groovy.transform.Field

import au.com.totemsoft.shoppingcart.domain.ShoppingCart;
import au.com.totemsoft.shoppingcart.domain.ShoppingCartItem;

/**
 * We are going to have a 3 for 2 deal on opera house ticket.
 * For example, if you buy 3 tickets,
 * you will pay the price of 2 only getting another one completely free of charge.
 * @param cart
 */
//void rule_OH(ShoppingCart cart) {
    int count = 0;
    for (ShoppingCartItem item : cart.items) {
        if (OPERA_HOUSE.equals(item.id)) {
            count++;
            if (count > 2) {
                item.price = BigDecimal.ZERO;
                System.out.println("\trule_OH promo");
            }
        }
    }
//}

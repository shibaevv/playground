//package promo;

import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.BRIDGE_CLIMB;
import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.OPERA_HOUSE;
import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.SKY_TOWER;

import java.math.BigDecimal;

import groovy.transform.Field

import au.com.totemsoft.shoppingcart.domain.ShoppingCart;
import au.com.totemsoft.shoppingcart.domain.ShoppingCartItem;

/**
 * The Sydney Bridge Climb will have a bulk discount applied,
 * where the price will drop to $20, if someone buys more than 4
 * @param cart
 */
//void rule_BC(ShoppingCart cart) {
    int count = 0;
    for (ShoppingCartItem item : cart.items) {
        if (BRIDGE_CLIMB.equals(item.id)) {
            count++;
            if (count > 4) {
                item.price = new BigDecimal("20.00");
                System.out.println("\trule_BC promo");
            }
        }
    }
//}

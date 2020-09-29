//package promo;

import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.BRIDGE_CLIMB;
import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.OPERA_HOUSE;
import static au.com.totemsoft.shoppingcart.domain.ShoppingCartItem.SKY_TOWER;

import java.math.BigDecimal;

import groovy.transform.Field

import au.com.totemsoft.shoppingcart.domain.ShoppingCart;
import au.com.totemsoft.shoppingcart.domain.ShoppingCartItem;

/**
 * We are going to give a free Sky Tower tour for with every Opera House tour sold
 * @param cart
 */
//void rule_SK(ShoppingCart cart) {
    int count = 0;
    for (ShoppingCartItem item : cart.items) {
        if (OPERA_HOUSE.equals(item.id)) {
            count++;
        }
    }
    if (count > 0) {
        for (ShoppingCartItem item : cart.items) {
            if (SKY_TOWER.equals(item.id)) {
                item.price = BigDecimal.ZERO;
                System.out.println("\trule_SK promo");
            }
        }
    }
//}

//package promo;

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
void rule_OH(ShoppingCart cart) {
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
}

/**
 * We are going to give a free Sky Tower tour for with every Opera House tour sold
 * @param cart
 */
void rule_SK(ShoppingCart cart) {
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
}

/**
 * The Sydney Bridge Climb will have a bulk discount applied,
 * where the price will drop to $20, if someone buys more than 4
 * @param cart
 */
void rule_BC(ShoppingCart cart) {
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
}

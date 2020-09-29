package au.com.totemsoft.shoppingcart.impl;

import java.math.BigDecimal;
import java.util.List;

import au.com.totemsoft.shoppingcart.BookingService;
import au.com.totemsoft.shoppingcart.domain.PromotionalRule;
import au.com.totemsoft.shoppingcart.domain.ShoppingCart;
import au.com.totemsoft.shoppingcart.exception.ShoppingCartException;
import au.com.totemsoft.shoppingcart.internal.PromotionalRuleService;

public class BookingServiceImpl implements BookingService {

    //@Inject
    private PromotionalRuleService promotionalRuleService;

    public BookingServiceImpl() throws Exception {
        promotionalRuleService = new PromotionalRuleServiceImpl();
    }

    @Override
    public ShoppingCart create() throws ShoppingCartException {
        return new ShoppingCart();
    }

    @Override
    public BigDecimal total(ShoppingCart cart) throws ShoppingCartException {
        final List<PromotionalRule> rules = promotionalRuleService.rules();
        promotionalRuleService.apply(cart, rules);
        return cart.getItems().stream()
            .map(i -> i.getPrice())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

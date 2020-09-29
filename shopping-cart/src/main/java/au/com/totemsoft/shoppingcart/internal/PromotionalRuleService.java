package au.com.totemsoft.shoppingcart.internal;

import java.util.List;

import au.com.totemsoft.shoppingcart.domain.PromotionalRule;
import au.com.totemsoft.shoppingcart.domain.ShoppingCart;
import au.com.totemsoft.shoppingcart.exception.PromotionalRuleException;

public interface PromotionalRuleService {

    List<PromotionalRule> rules() throws PromotionalRuleException;

    void apply(ShoppingCart cart, List<PromotionalRule> rules) throws PromotionalRuleException;

}

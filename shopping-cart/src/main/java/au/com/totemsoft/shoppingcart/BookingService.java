package au.com.totemsoft.shoppingcart;

import java.math.BigDecimal;

import au.com.totemsoft.shoppingcart.domain.ShoppingCart;
import au.com.totemsoft.shoppingcart.exception.ShoppingCartException;

public interface BookingService {

    ShoppingCart create() throws ShoppingCartException;

    BigDecimal total(ShoppingCart cart) throws ShoppingCartException;

}

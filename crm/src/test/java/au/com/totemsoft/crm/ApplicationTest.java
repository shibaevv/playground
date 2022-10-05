package au.com.totemsoft.crm;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

//@ActiveProfiles("test")
class ApplicationTest {

    @Test
    @Order(1)
    public void contextLoads() {
        // test spring context load
    }

}

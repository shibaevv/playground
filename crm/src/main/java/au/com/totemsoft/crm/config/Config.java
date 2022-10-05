package au.com.totemsoft.crm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "au.com.totemsoft.crm.jpa")
@EnableTransactionManagement
public class Config {

}

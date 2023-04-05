package ms.spring.crudbasic.api.infrastructure.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfiguration {
    @Autowired
    private Environment environment;

    @Bean
    public void printEnvironment() {
        if (!isEnvironmentProduction()) {
            System.out.println("AppConfiguration - Environment development");
        } else {
            System.out.println("AppConfiguration - Environment production");
        }
    }

    @Bean
    public boolean isEnvironmentProduction() {
        return environment.getActiveProfiles().length > 0;
    }
}

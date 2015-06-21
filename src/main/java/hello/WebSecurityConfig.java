package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


@Configuration
public class WebSecurityConfig {
     
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new AuthenticationManagerBuilder(new NopPostProcessor())
                       .inMemoryAuthentication().withUser("user").password("password").roles("USER")
                       .and().and().build();
    }
     
    private static class NopPostProcessor implements ObjectPostProcessor {
        @Override
        @SuppressWarnings("unchecked")
        public Object postProcess(Object object) {
            return object;
        }
    };
}

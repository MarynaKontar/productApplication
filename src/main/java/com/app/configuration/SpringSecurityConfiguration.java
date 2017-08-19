package com.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
 * Spring security configuration with Basic login form.
 */
@Configuration
@EnableWebSecurity
@ComponentScan("com.app.service")
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    // We must define way how we know informations about user.
    // UserDetailsService simply loads all information about user by username.
    // We will use BC password encoder and http basic configuration.
    // Configure this all by configure method.

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}


//    @Bean
//    protected UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(User.withUsername("test").password("test").roles("USER").build());
//        manager.createUser(User.withUsername("testAdmin").password("testAdmin").roles("ADMIN").build());
//        return manager;
//    }

    // Main entry for configuration of the security.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Basic idea is next - create controller where:
        // 1. USER can check all products in the system.
        // 2. ADMIN can create, update or remove products.

        http.authorizeRequests().antMatchers("/product/list").hasAnyRole("USER", "ADMIN")
                .antMatchers("/product/**").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();

        // This means that all request must be authenticated by form login and http basic (for case of post message).
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
//             .and()
//                .formLogin()
//             .and()
//                .httpBasic()
//             .and()
//                .csrf()
//                .disable();
    }

//Можно самому написать AuthenticationProvider, т.е. новый способ аутентификации user. Для этого надо создать бин (newProvider()).
// А AuthenticationManager сам подтянет его в свой список провайдеров  и его можно будет использовать для авторизации.
//    @Bean
//    public AuthenticationProvider newProvider(){
//        return new AuthenticationProvider() {
//
//
//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                return null;
//            }
//
//            //поддерживается ли текущая авторизация
//            @Override
//            public boolean supports(Class<?> authentication) {
//                return true;
//            }
//        };
//    }
}

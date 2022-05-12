package com.slodkacysia.bakeryshop.configuration;

import com.slodkacysia.bakeryshop.service.SpringDataUserDetailsService;
import com.slodkacysia.bakeryshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("tomasz.czornak59@gmail.com").password("pass").authorities("ROLE_ADMIN");

    }
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//    @Autowired
//    private DataSource dataSource;

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ADMIN')").and().formLogin().loginPage("/admin/login").defaultSuccessUrl("/admin/panel").and().authorizeRequests()
                .antMatchers("/user/**").access("hasRole('USER')").and().formLogin().loginPage("/login").defaultSuccessUrl("/user/home").and().authorizeRequests()
                .antMatchers("/").permitAll();

//                .and().logout().logoutSuccessUrl("/login?logout")
//                .and().formLogin().loginPage("/login").successHandler(savedRequestAwareAuthenticationSuccessHandler())
//                .loginProcessingUrl( "/j_spring_security_check" )
//                .failureUrl("/login?error")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/");
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
//                .rememberMe().key("remember-me").rememberMeParameter("remember-me").rememberMeCookieName("remember-me");.tokenRepository(persistentTokenRepository()).tokenValiditySeconds(1209600);
    }

//    private PersistentTokenRepository persistentTokenRepository() {
//
//        JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
//        jdbcTokenRepositoryImpl.setDataSource(dataSource);
//        return jdbcTokenRepositoryImpl;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringDataUserDetailsService customUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {

        SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();

        return authenticationSuccessHandler;
    }

}

//Tomasz --
package ro.var.libmngmt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("SELECT username,user_password,enabled FROM libraryWebDB.users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username,user_role FROM libraryWebDB.users WHERE username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/login", "/welcome").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler())
                .failureUrl("/login.html?error=true")
                .and()
                .logout().deleteCookies("JSESSIONID")
                .and()
                .rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400)
                .and()
                .csrf().disable()
                .logout()
                .logoutSuccessUrl("/login")
        ;
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new SimpleAuthenticationSuccessHandler();
    }

}

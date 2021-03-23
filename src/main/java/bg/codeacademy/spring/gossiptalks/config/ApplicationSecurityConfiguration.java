package bg.codeacademy.spring.gossiptalks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter
{
  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/login", "/register").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin().loginPage("/login").permitAll()
        .usernameParameter("username")
        .passwordParameter("password")
        .defaultSuccessUrl("/home.html", true)
        .and()
        .logout().logoutSuccessUrl("/login?logout").permitAll();
  }

//  @Bean
//  public PasswordEncoder passwordEncoder()
//  {
//    return new BCryptPasswordEncoder();
//  }

}

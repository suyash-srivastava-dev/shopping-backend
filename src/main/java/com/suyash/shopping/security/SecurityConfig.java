package com.suyash.shopping.security;

import com.suyash.shopping.security.Filter.CustomAuthenticationFilter;
import com.suyash.shopping.security.Filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
//    private final BCryptPasswordEncoder passwordEncoder;
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeHttpRequests().antMatchers(POST,"/v1/api/outlet/create").hasAnyAuthority("admin");
        http.authorizeHttpRequests().antMatchers(POST,"/v1/api/outlet/create/list").hasAnyAuthority("admin");
        http.authorizeHttpRequests().antMatchers(POST,"/v1/api/item/create/list").hasAnyAuthority("admin");
        http.authorizeHttpRequests().antMatchers(POST,"/api/item/create").hasAnyAuthority("admin");
        http.authorizeHttpRequests().antMatchers(POST,"/api/user/signup").permitAll();
        http.authorizeHttpRequests().antMatchers(POST,"/login").permitAll();
        http.authorizeHttpRequests().antMatchers(POST,"/v1/api/location/distance").hasAnyAuthority("admin","customer");
        http.authorizeHttpRequests().antMatchers(POST,"/v1/api/location/outlet/**").hasAnyAuthority("admin","customer");


//        http.authorizeHttpRequests().anyRequest().permitAll();
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}


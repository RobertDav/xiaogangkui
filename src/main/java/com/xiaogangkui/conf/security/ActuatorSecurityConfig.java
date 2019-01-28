package com.xiaogangkui.conf.security;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.context.ShutdownEndpoint;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//protected void configure(HttpSecurity http) throws Exception {
//        http
//        .authorizeRequests()                                                                1
//        .antMatchers("/resources/**", "/signup", "/about").permitAll()                  2
//        .antMatchers("/admin/**").hasRole("ADMIN")                                      3
//        .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")            4
//        .anyRequest().authenticated()                                                   5
//        .and()
//        // ...
//        .formLogin();
//        }
//        1There are multiple children to the http.authorizeRequests() method each matcher is considered in the order they were declared.
//        2We specified multiple URL patterns that any user can access. Specifically, any user can access a request if the URL starts with "/resources/", equals "/signup", or equals "/about".
//        3Any URL that starts with "/admin/" will be restricted to users who have the role "ROLE_ADMIN". You will notice that since we are invoking the hasRole method we do not need to specify the "ROLE_" prefix.
//        4Any URL that starts with "/db/" requires the user to have both "ROLE_ADMIN" and "ROLE_DBA". You will notice that since we are using the hasRole expression we do not need to specify the "ROLE_" prefix.
//        5Any URL that has not already been matched on only requires that the user be authenticated
@Configuration
public class ActuatorSecurityConfig  extends WebSecurityConfigurerAdapter {
    /*
        This spring security configuration does the following

        1. Restrict access to the Shutdown endpoint to the ACTUATOR_ADMIN role.
        2. Allow access to all other actuator endpoints.
        3. Allow access to static resources.
        4. Allow access to the home page (/).
        5. All other requests need to be authenticated.
        5. Enable http basic authentication to make the configuration complete.
           You are free to use any other form of authentication.
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .requestMatchers(EndpointRequest.to(ShutdownEndpoint.class)).hasRole("ACTUATOR_ADMIN")
            .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .antMatchers("/**").permitAll()
            .antMatchers("/actuator/**").authenticated()
            .and()
            .httpBasic();
    }
}

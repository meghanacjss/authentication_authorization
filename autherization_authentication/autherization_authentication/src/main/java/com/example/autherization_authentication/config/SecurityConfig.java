package com.example.autherization_authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {

//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
// return  http.csrf().disable()
//            .authorizeHttpRequests()
//            .requestMatchers("/user/welcome").permitAll()
//            .and()
//            .authorizeHttpRequests().requestMatchers("/user/**")
//            .authenticated()
//            .and().formLogin()
//            .and().build();
//}

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/user/start","/user/adduser").permitAll())
//                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/user/**").authenticated()).httpBasic(Customizer.withDefaults());
//        return http.build();
//
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers(HttpMethod.POST,"/adduser").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/userName").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.GET,"/allUsers").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/update{userid}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/delete{userid}").hasAnyRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());
        //disable cross Site request Forgery(CSRF)
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}

    @Bean
    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.builder().username("meghana")
//                .password(passwordEncoder().encode("pwd1"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.builder().username("Meghana")
//                .password(passwordEncoder().encode("pwd2"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin,user);
        return new UserUserDetailsService();
    }
@Bean
public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;

 }

}

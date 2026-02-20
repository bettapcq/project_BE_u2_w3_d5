package bettapcq.projectu2w3d5.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {


        //disabilitare autenticazioni che non ci servono:

        //no autenticazione per il login:
        httpSecurity.formLogin(formLogin -> formLogin.disable());

        //no protezione da scrf:
        httpSecurity.csrf(
                csrf -> csrf.disable());

        //disabilito statefull:
        httpSecurity.sessionManagement(
                sessions -> sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        //no protezione di default su tutti gli endpoint:
        httpSecurity.authorizeHttpRequests(
                req -> req.requestMatchers("/**").permitAll());


        return httpSecurity.build();
    }

    //conversione delle pwd in bCrypt
    @Bean
    public PasswordEncoder getBCript() {
        return new BCryptPasswordEncoder(12);
    }

}
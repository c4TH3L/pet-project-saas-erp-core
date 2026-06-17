package ru.cathel.saaserpcore.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import ru.cathel.saaserpcore.auth.dto.EmployeeAccessTokenClaims;
import ru.cathel.saaserpcore.auth.dto.EmployeeRefreshTokenClaims;
import ru.cathel.saaserpcore.auth.dto.FounderAccessTokenClaims;
import ru.cathel.saaserpcore.auth.dto.FounderRefreshTokenClaims;
import ru.cathel.saaserpcore.auth.service.*;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .requestCache(AbstractHttpConfigurer::disable)
                .securityContext(s -> s.requireExplicitSave(false))
                .authorizeHttpRequests(a ->
                        a.anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public JwtClaimsMapper<FounderAccessTokenClaims> founderAccessClaimsMapper() {
        return new FounderAccessTokenClaimsMapper();
    }

    @Bean
    public JwtClaimsMapper<FounderRefreshTokenClaims> founderRefreshClaimsMapper() {
        return new FounderRefreshTokenClaimsMapper();
    }

    @Bean
    public JwtClaimsMapper<EmployeeAccessTokenClaims> employeeAccessClaimsMapper() {
        return new EmployeeAccessTokenClaimsMapper();
    }

    @Bean
    public JwtClaimsMapper<EmployeeRefreshTokenClaims> employeeRefreshClaimsMapper() {
        return new EmployeeRefreshTokenClaimsMapper();
    }
}

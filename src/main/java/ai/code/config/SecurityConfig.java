package ai.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final String[] freeResourceUrls = {"/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/api-docs/**", "/aggregate/**"};
    @Bean
    public SecurityFilterChain publicSecurity(HttpSecurity http) throws Exception {
        http
                .securityMatcher(
                        "/swagger-ui.html",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/api-docs/**",
                        "/aggregate/**"
                )
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }

    @Bean
    public SecurityFilterChain securedSecurity(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}

package dev.ayelen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import dev.ayelen.security.JpaUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

        @Value("${api-endpoint}")
        private String apiEndpoint;

        private JpaUserDetailsService jpaUserDetailsService;

        public SecurityConfiguration(JpaUserDetailsService jpaUserDetailsService) {
                this.jpaUserDetailsService = jpaUserDetailsService;
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .cors(Customizer.withDefaults())
                                .csrf(csrf -> csrf.disable())
                                .formLogin(form -> form.disable())
                                .authorizeHttpRequests(
                                                auth -> auth.requestMatchers(
                                                                AntPathRequestMatcher.antMatcher("/h2-console/**"))
                                                                .permitAll()
                                                                .requestMatchers(HttpMethod.GET, apiEndpoint + "/**")
                                                                .hasAnyRole("USER", "ADMIN")
                                                                .anyRequest().authenticated())
                                .userDetailsService(jpaUserDetailsService)
                                .httpBasic(Customizer.withDefaults());
                http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));
                return http.build();

        }

        @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}

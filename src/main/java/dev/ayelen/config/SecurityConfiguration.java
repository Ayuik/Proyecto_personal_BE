package dev.ayelen.config;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import dev.ayelen.security.JpaUserDetailsService;
import dev.ayelen.security.JwtAuthenticationFilter;
import dev.ayelen.security.JwtTokenProvider;

@Configuration
@EnableWebSecurity
@Profile("!test")
public class SecurityConfiguration {

        @Value("${api-endpoint}")
        private String apiEndpoint;

        @Value("${jwt.key}")
    private String key;

        private final JpaUserDetailsService jpaUserDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    
    public SecurityConfiguration(JpaUserDetailsService jpaUserDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.jpaUserDetailsService = jpaUserDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                .cors(Customizer.withDefaults())
                                .csrf(csrf -> csrf.disable())
                                .formLogin(form -> form.disable())
                                .authorizeHttpRequests(
                                                auth -> auth.requestMatchers(
                                                                AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                                                                .requestMatchers(HttpMethod.GET, apiEndpoint + "/**").permitAll()
                                                                .requestMatchers(HttpMethod.POST, apiEndpoint + "/login").permitAll()
                                                                .requestMatchers(HttpMethod.POST, apiEndpoint + "/register").permitAll()
                                                                .requestMatchers(HttpMethod.PUT, apiEndpoint + "/categories/**").hasRole("ADMIN")
                                                                .requestMatchers(HttpMethod.POST, apiEndpoint + "/categories").hasRole("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE, apiEndpoint + "/categories").hasRole("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT, apiEndpoint + "/courses/**").hasRole("ADMIN")
                                                                .requestMatchers(HttpMethod.POST, apiEndpoint + "/courses").hasRole("ADMIN")
                                                                .requestMatchers(HttpMethod.DELETE, apiEndpoint + "/courses/**").hasRole("ADMIN")
                                                                .requestMatchers(HttpMethod.PUT, apiEndpoint + "/videos/**").hasRole("ADMIN")
                                                                .anyRequest().authenticated())
                                .userDetailsService(jpaUserDetailsService)
                
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                                .httpBasic(Customizer.withDefaults());

                                                http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));
                                                http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, jpaUserDetailsService),
            UsernamePasswordAuthenticationFilter.class);
                                                return http.build();

        }

        @Bean
        PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(key.getBytes()));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] bytes = key.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(bytes, 0, bytes.length, "RSA");
        return NimbusJwtDecoder.withSecretKey(secretKey).macAlgorithm(MacAlgorithm.HS512).build();
    }   

}

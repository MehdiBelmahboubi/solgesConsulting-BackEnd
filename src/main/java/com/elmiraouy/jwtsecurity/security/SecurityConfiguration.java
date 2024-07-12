package com.elmiraouy.jwtsecurity.security;

import com.elmiraouy.jwtsecurity.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler ;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .logout(httpSecurityLogoutConfigurer ->
                        {
                            httpSecurityLogoutConfigurer.logoutUrl("/api/auth/logout");
                            httpSecurityLogoutConfigurer.addLogoutHandler(logoutHandler);

                            httpSecurityLogoutConfigurer.logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder.clearContext()
                            );
                        }
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/client/auth/**","/api/client/cloudinary/*","/api/client/**","/api/client/users/register")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                /*.exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                            httpSecurityExceptionHandlingConfigurer
                                    .authenticationEntryPoint((request, response, authException) -> {
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                            });
                        }
                )*/
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                    httpSecurityExceptionHandlingConfigurer
                            .authenticationEntryPoint((request, response, authException) -> {
                                // Personnaliser la réponse pour renvoyer des détails sur l'exception
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.setContentType("application/json");
                                response.getWriter().write("{\"error\": \"" + authException.getMessage() + "\"}");
                            });
                })
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
               // .rememberMe(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

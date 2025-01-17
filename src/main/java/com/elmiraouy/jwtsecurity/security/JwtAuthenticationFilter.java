package com.elmiraouy.jwtsecurity.security;
import com.elmiraouy.jwtsecurity.repository.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
      final String authHeader =request.getHeader("Authorization");
      final String jwt;
        System.out.println("*******authHeader*********"+authHeader);

        final String userEmail ;
      if(authHeader == null || !authHeader.startsWith("Bearer ") ){
          System.out.println("*******not Bearer 1*********");
          filterChain.doFilter(request,response);
          return;
      }

      jwt=authHeader.substring(7);
      System.out.println("---"+jwt);
      userEmail= jwtService.extractUserEmail(jwt);
      if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){

          System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&  "+userEmail);

          UserDetails userDetails =this.userDetailsService.loadUserByUsername(userEmail);
          var isTokenValid =tokenRepository.findValidTokenByUser(userEmail);


          System.out.println("!!!!!!!!!!!========================="+isTokenValid.getAccessToken());
          if(jwtService.isTokenValid(jwt,userDetails) && isTokenValid  != null ){
              System.out.println("!!!!!!!!!!!== tous valiser =============");
              UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(
                      userDetails,
                      null,
                      userDetails.getAuthorities()
              );
              authToken.setDetails(
                      new WebAuthenticationDetailsSource().buildDetails(request)
              );
              SecurityContextHolder.getContext().setAuthentication(authToken);
          }
      }
      filterChain.doFilter(request,response);
    }
}

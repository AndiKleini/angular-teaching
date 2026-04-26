package at.smarthome.filter;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.smarthome.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final AuthenticationService authService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        this.log.info("JwtAuthenticationFilter: doFilterInternal called for request " + request.getRequestURI());

        final String authHeader = request.getHeader("Authorization");
        final String jwt;

        // 1. Check for Bearer token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            this.log.info("JwtAuthenticationFilter: No Bearer token found, skipping authentication");
            return;
        }

        jwt = authHeader.substring(7);

        Long userId = this.authService.validate(jwt);
        if (userId != 0L) {
             this.log.info("JwtAuthenticationFilter: Valid JWT token found, setting authentication context");
             UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(
                        userId, null, List.of(() -> "ROLE_USER"));
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        } else {
            this.log.info("JwtAuthenticationFilter: No Valid JWT token found");
        }
      
       filterChain.doFilter(request, response);
    }
}

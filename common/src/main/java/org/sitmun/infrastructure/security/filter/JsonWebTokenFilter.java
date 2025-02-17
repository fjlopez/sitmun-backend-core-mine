package org.sitmun.infrastructure.security.filter;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.sitmun.infrastructure.security.service.JsonWebTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonWebTokenFilter extends OncePerRequestFilter {

  private final JsonWebTokenService jsonWebTokenService;
  private final UserDetailsService userDetailsService;

  public JsonWebTokenFilter(
    @Autowired UserDetailsService userDetailsService,
    @Autowired JsonWebTokenService jsonWebTokenService) {
    this.userDetailsService = userDetailsService;
    this.jsonWebTokenService = jsonWebTokenService;
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
    final String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (StringUtils.startsWith(requestTokenHeader, "Bearer ")) {
      String jwtToken = requestTokenHeader.substring(7);
      try {
        String username = jsonWebTokenService.getUsernameFromToken(jwtToken);
        if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
          if (jsonWebTokenService.validateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          }
        }
      } catch (IllegalArgumentException e) {
        logger.error("Unable to fetch JWT Token");
      } catch (ExpiredJwtException e) {
        logger.error("JWT Token is expired");
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    } else {
      logger.warn("JWT Token does not begin with Bearer String");
    }
    filterChain.doFilter(request, response);
  }

}
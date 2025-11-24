package com.academia.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        // El context-path es /api, así que las rutas son /api/auth/*, /api/health
        return path.startsWith("/api/auth/") ||
                path.equals("/api/health") ||
                path.startsWith("/auth/") ||
                path.equals("/health") ||
                path.equals("/api/health");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            // Obtener el token JWT del header o cookies
            String token = getJWTFromRequest(request);
            logger.debug("Token extraído: " + (token != null ? "presente" : "null"));

            // Validar token
            if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
                // Obtener email del token
                String email = jwtTokenProvider.getEmailFromJWT(token);
                logger.debug("Email del token: " + email);

                // Cargar el usuario asociado al token
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                logger.debug("Usuario cargado: " + userDetails.getUsername() + ", roles: " + userDetails.getAuthorities());

                UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establecer la autenticación en el contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                logger.debug("Autenticación establecida en SecurityContext");
            } else {
                logger.debug("Token no válido o ausente");
            }
        } catch (Exception ex) {
            logger.error("No se pudo establecer la autenticación del usuario en el contexto de seguridad", ex);
        }

        filterChain.doFilter(request, response);
    }

    // Método para extraer el JWT del header Authorization o de las cookies
    private String getJWTFromRequest(HttpServletRequest request) {
        // Primero intentar obtener del header Authorization
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        // Si no está en el header, buscar en las cookies
        if (request.getCookies() != null) {
            logger.debug("Cookies encontradas: " + request.getCookies().length);
            for (jakarta.servlet.http.Cookie cookie : request.getCookies()) {
                logger.debug("Cookie: " + cookie.getName() + " = " + cookie.getValue());
                if ("token".equals(cookie.getName())) {
                    logger.debug("Token encontrado en cookie: " + cookie.getValue());
                    return cookie.getValue();
                }
            }
        } else {
            logger.debug("No se encontraron cookies");
        }

        return null;
    }
}
package br.com.techsync.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final String chaveSecreta = "MinhaChaveSuperSecreta1234567890123456";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // --- ADIÇÃO AQUI: Permitir requisições OPTIONS ---
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK); // Retorna 200 OK para a requisição OPTIONS
            filterChain.doFilter(request, response); // Permite que a requisição siga
            return; // Encerra a execução do filtro para OPTIONS
        }
        // --- FIM DA ADIÇÃO ---

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Remove o "Bearer "

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(chaveSecreta.getBytes())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                String email = claims.getSubject();
                request.setAttribute("email", email);

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token JWT inválido ou expirado.");
                return;
            }

        } else {
            if (isProtectedEndpoint(request)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token JWT ausente.");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isProtectedEndpoint(HttpServletRequest request) {
        String path = request.getRequestURI();
        String method = request.getMethod();

        // Login não precisa de token
        if (path.equals("/api/usuarios/login")) {
            return false;
        }

        // Cadastro (POST) não precisa de token
        if (path.equals("/api/usuarios") && method.equals("POST")) {
            return false;
        }

        // Todos os outros endpoints /api/usuarios exigem token
        return path.startsWith("/api/usuarios");
    }
}
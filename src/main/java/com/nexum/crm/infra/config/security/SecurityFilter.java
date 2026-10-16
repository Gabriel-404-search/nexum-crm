package com.nexum.crm.infra.config.security;

import com.nexum.crm.infra.persistence.user.UserEntity;
import com.nexum.crm.infra.persistence.user.UserRepositoryImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepositoryImpl userRepository;

    public SecurityFilter(UserRepositoryImpl userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Recupera o token do cabeçalho da requisição
        String token = this.recoverToken(request);

        if(token != null) {
            // 2. Valida e extrai o login (email) de dentro do token
            String login = tokenService.validateToken(token);

            if (!login.isEmpty()) {
                // 3. Busca o usuário no banco de dados
                UserEntity userEntity = userRepository.findByEmail(login);

                if (userEntity != null) {
                    // 4. Envolve a entidade no adaptador SecurityUser
                    UserDetails userDetails = new SecurityUser(userEntity);

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    // 5. Salva autenticação no contexto do Spring Security
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        // 6. ADICIONADO: Permite que a requisição siga seu fluxo normal para o Controller
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        // Pega o valor enviado no cabeçalho HTTP "Authorization"
        String authHeader = request.getHeader("Authorization");

        // Se não houver cabeçalho Authorization, significa que o usuário não mandou token
        if(authHeader == null) return null;

        // Remove a palavra "Bearer " do início da string, restando apenas o token JWT puro
        return authHeader.replace("Bearer ", "");
    }
}
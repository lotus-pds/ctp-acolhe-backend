package br.edu.ifsp.spo.ctpacolhe.common.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.edu.ifsp.spo.ctpacolhe.common.constant.MensagemExceptionType;
import br.edu.ifsp.spo.ctpacolhe.common.exception.ValidationException;
import br.edu.ifsp.spo.ctpacolhe.common.security.ApplicationSecurity;
import br.edu.ifsp.spo.ctpacolhe.entity.Usuario;
import br.edu.ifsp.spo.ctpacolhe.repository.UsuarioRepository;

@Component
public class UsuarioAtivoFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    private final List<AntPathRequestMatcher> requestMatchers;

    public UsuarioAtivoFilter() {
        this.requestMatchers = ApplicationSecurity.ACCOUNT_OPEN_PATHS.stream().map(AntPathRequestMatcher::new)
                .collect(Collectors.toList());
        
        this.requestMatchers.add(new AntPathRequestMatcher("/usuario/autenticado/reativar"));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        boolean isRequisicaoAberta = requestMatchers.stream().anyMatch(matcher -> matcher.matches(request));

        if (!isRequisicaoAberta) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();

            Usuario usuarioAutenticado = usuarioRepository.findByEmail(email)
                    .orElseThrow(() -> new ValidationException(MensagemExceptionType.USUARIO_NAO_ENCONTRADO));

            if (!usuarioAutenticado.isAtivo()) {
            	throw new ValidationException(MensagemExceptionType.USUARIO_INATIVO);
            }
        }
        filterChain.doFilter(request, response);
    }
}

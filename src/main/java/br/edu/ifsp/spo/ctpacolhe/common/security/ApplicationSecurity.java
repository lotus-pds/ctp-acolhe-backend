package br.edu.ifsp.spo.ctpacolhe.common.security;

import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.edu.ifsp.spo.ctpacolhe.common.filter.ExceptionHandlerFilter;
import br.edu.ifsp.spo.ctpacolhe.common.jwt.JwtTokenFilter;

@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ApplicationSecurity {
	
	public static final List<String> ACCOUNT_OPEN_PATHS = List.of(
			"/conta/cadastro",
			"/conta/cadastro/verificacao/{token}",
			"/conta/cadastro/reenviar-email",
			"/conta/acesso",
			"/conta/senha/esqueci",
			"/conta/senha/esqueci/reenviar-email",
			"/conta/senha/redefinir",
			"/conta/renovar-token",
			"/curso",
			"/v3/**",
			"/doc/swagger-ui/**"
    );

	public AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private ExceptionHandlerFilter exceptionHandlerFilter;

	@Autowired
	private JwtTokenFilter jwtTokenFilter;

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	    
    @Bean
    public TimeZone timeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
        return TimeZone.getTimeZone("America/Sao_Paulo");
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService);
		authenticationManager = authenticationManagerBuilder.build();

        http.cors().and().csrf()
                .disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeHttpRequests((authz) -> authz
                .antMatchers(ACCOUNT_OPEN_PATHS.toArray(String[]::new)).permitAll()
                .antMatchers("/usuario/autenticado/**").hasAnyAuthority("Admin", "Aluno")
                .antMatchers("/usuario/autenticado/humor/**").hasAnyAuthority("Aluno")
                .antMatchers("/usuario", "/usuario/{idUsuario}/perfil", "/incidente/**").hasAnyAuthority("Admin")
                .anyRequest().authenticated()
             );
		
        http.exceptionHandling(handling -> handling.authenticationEntryPoint((request, response, ex) -> {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        }));

		http.authenticationManager(authenticationManager);
		
		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		
		http.addFilterBefore(exceptionHandlerFilter, JwtTokenFilter.class);
		
		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}

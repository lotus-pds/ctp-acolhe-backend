package br.edu.ifsp.spo.ctpacolhe.common.security;

import java.util.List;

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

import br.edu.ifsp.spo.ctpacolhe.common.jwt.JwtTokenFilter;

@Configuration
@EnableWebSecurity(debug = false)
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ApplicationSecurity {

	public AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenFilter jwtTokenFilter;

    @Bean
    PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userDetailsService);
		authenticationManager = authenticationManagerBuilder.build();

		var accountOpenPaths = List.of(
				"/conta/cadastro",
				"/conta/acesso"
        );

        http.cors().and().csrf()
                .disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeHttpRequests((authz) -> authz
                .antMatchers(accountOpenPaths.toArray(String[]::new)).permitAll()
                .antMatchers("/humor/**").hasAnyAuthority("Aluno")
                .anyRequest().authenticated()
             );
		
        http.exceptionHandling(handling -> handling.authenticationEntryPoint((request, response, ex) -> {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        }));

		http.authenticationManager(authenticationManager);

		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}

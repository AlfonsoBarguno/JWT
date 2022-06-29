package com.securityJasonWebToken.JWT.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.securityJasonWebToken.JWT.config.MyUserDetailsService;
import com.securityJasonWebToken.JWT.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	//interceptará cada request y examinará el header
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authoritationHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		//si el header no es null y empieza con Bearer
		if(authoritationHeader != null && authoritationHeader.startsWith("Bearer ")) {
			
			//extraemos el jwt después de "Bearer " (7)
			jwt = authoritationHeader.substring(7);
			//extraemos el nombre del jwt
			username = jwtUtil.extractUsername(jwt);
		}
		
		//lo incorporamos a userDetails
		//y verificamos que nada haya ido al SecurityContext
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			
			//validamos el token comprobando nombre y fecha
			//si es válido, simulamos una operación normal
			
			if(jwtUtil.validateToken(jwt, userDetails)) {
				
				
				//creamos el token por defecto
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken
				.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				//lo colocamos en el contexto
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
	
	

}

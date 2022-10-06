package br.com.bank.authenticator.config;

import br.com.bank.authenticator.entity.UserAccountEntity;
import br.com.bank.authenticator.service.JwtUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	static final Logger log = LogManager.getLogger(JwtRequestFilter.class.getName());
	private final JwtUserDetailsService jwtUserDetailsService;
	private final JwtTokenUtil jwtTokenUtil;

	public JwtRequestFilter(JwtUserDetailsService jwtUserDetailsService, JwtTokenUtil jwtTokenUtil) {
		this.jwtUserDetailsService = jwtUserDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				log.info("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				log.info("JWT Token has expired");
			}
		} else {
			log.warn("JWT Token does not begin with Bearer String");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(jwtToken, userDetails))) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		if(jwtToken != null) {
			Claims claims = jwtTokenUtil.getAllClaimsFromToken(jwtToken);
			request.setAttribute("UserAccount", getUser(claims));
		}
		
		chain.doFilter(request, response);
	}

	private UserAccountEntity getUser(Claims claims) {
		@SuppressWarnings("unchecked")
		var account = (Map<String, Object>) claims.get("account");
		return new UserAccountEntity()
					.setDocument(claims.get("sub").toString())
					.setNumber(account.get("number").toString())
					.setDigit(account.get("digit").toString())
					.setAgency(account.get("agency").toString());
	}
	
}

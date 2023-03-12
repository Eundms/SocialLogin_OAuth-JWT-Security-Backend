package com.basic.template.security.jwt;


import com.basic.template.jwtexample.user.domain.User;
import com.basic.template.jwtexample.user.domain.UserRepository;
import com.basic.template.security.config.PrincipalDetails;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
@Slf4j
// 인가
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	private UserRepository userRepository;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(jwtTokenProvider.getHEADER_STRING());
		log.debug("header Authorization : {}" , header);
		
		if (header == null || !header.startsWith(jwtTokenProvider.getTOKEN_PREFIX())) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = request.getHeader(jwtTokenProvider.getHEADER_STRING()).replace(jwtTokenProvider.getTOKEN_PREFIX(), "");
		log.debug("Token : {}" , token);

		// 토큰 검증
		String username = jwtTokenProvider.getUserName(token);
		if (username != null) {
			User user = userRepository.findByUsername(username);

			PrincipalDetails principalDetails = new PrincipalDetails(user);
			Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails,
					null,
					principalDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}

}

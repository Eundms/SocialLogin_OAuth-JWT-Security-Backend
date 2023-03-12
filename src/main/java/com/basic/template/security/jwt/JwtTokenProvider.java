package com.basic.template.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.basic.template.jwtexample.user.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenProvider {
    @Value("${custom.jwt.secret}")
    private String SECRET = null; // 우리 서버만 알고 있는 비밀값
    private int EXPIRATION_TIME = 864000000;
    private String TOKEN_PREFIX = "Bearer ";
    private String HEADER_STRING = "Authorization";
    public String create(User user) {
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .sign(Algorithm.HMAC512(SECRET));

        return token;
    }
    public String getUserName(String token){
        return JWT.require(Algorithm.HMAC512(SECRET)).build().verify(token)
                .getClaim("username").asString();
    }

    public Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }

    public String getHEADER_STRING(){
        return HEADER_STRING;
    }
    public String getTOKEN_PREFIX(){
        return TOKEN_PREFIX;
    }
}
package com.nonamed.farm.global.jwt;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.nonamed.farm.domain.refresh.domain.RefreshToken;
import com.nonamed.farm.domain.refresh.domain.repository.RefreshTokenRepository;
import com.nonamed.farm.domain.user.exception.NotRefreshTokenException;
import com.nonamed.farm.global.jwt.details.Details;
import com.nonamed.farm.global.jwt.details.DetailsService;
import com.nonamed.farm.global.jwt.exception.TokenUnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    @Value("${auth.jwt.exp.access}")
    private Long accessTokenTime;

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshTokenTime;

    private final DetailsService detailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public String getRefreshToken(String userId) {
        return refreshTokenRepository.save(
                RefreshToken.builder()
                        .id(userId)
                        .refreshToken(generateRefreshToken(userId))
                        .build()
        ).getRefreshToken();
    }

    public String generateToken(String userId) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenTime * 1000))
                .setIssuedAt(new Date())
                .setHeaderParam("typ", "access")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(userId)
                .compact();
    }

    public LocalDateTime getExpiryTime() {
        return LocalDateTime.now().plusSeconds(accessTokenTime);
    }

    public String generateRefreshToken(String userId) {
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenTime * 1000))
                .setIssuedAt(new Date())
                .setHeaderParam("typ", "refresh")
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(userId)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Details details = detailsService.loadUserByUsername(subject(token));
        return new UsernamePasswordAuthenticationToken(details,"",details.getAuthorities());
    }

    public boolean validateToken(String token) {
        try {
            return getBody(token).getExpiration().after(new Date());
        } catch (Exception e) {
            throw TokenUnauthorizedException.EXCEPTION;
        }
    }

    public boolean isRefreshToken(String token) {
        try {
            return getHeader(token).get("typ").equals("refresh_token");
        } catch (Exception e) {
            throw NotRefreshTokenException.EXCEPTION;
        }
    }

    private Claims getBody(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private String subject(String token) {
        try {
            return getBody(token).getSubject();
        } catch (Exception e) {
            throw TokenUnauthorizedException.EXCEPTION;
        }
    }

    private JwsHeader getHeader(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getHeader();
    }

}

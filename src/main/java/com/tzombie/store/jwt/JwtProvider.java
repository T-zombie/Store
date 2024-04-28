package com.tzombie.store.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.springframework.util.StringUtils.hasText;

@Slf4j
@Component
public class JwtProvider {

    private final String jwtSecret = "TWF4aW11bUdldEtleUZvclNwb29uQW5kU2FsdDU1NUAkZm9yJE1vbmV5V2F0Y2hGb3JZb3VyRGF5cyQkUmF5TWFudGFMb3N0Iw==";

    public String generateToken(@NonNull String login) {
        var date = new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));

        return Jwts
                .builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(@Nullable String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("Token expired");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            log.error("Unsupported jwt");
        } catch (MalformedJwtException malformedJwtException) {
            log.error("Malformed jwt");
        } catch (IllegalArgumentException illegalArgumentException) {
            log.error("Invalid token");
        }
        return false;
    }

    public String getLoginFromToken(@Nullable String token) {
        var claims = Jwts
                .parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    @Nullable
    public String getTokenFromHeader(@Nullable String bearer) {
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

}

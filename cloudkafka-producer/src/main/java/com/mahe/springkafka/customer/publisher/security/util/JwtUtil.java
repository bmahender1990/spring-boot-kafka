package com.mahe.springkafka.customer.publisher.security.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Service
public class JwtUtil {
  private static final Logger LOG = LogManager.getLogger(JwtUtil.class);
  @Value("${token_exp_mins}")
  private int tokenExpMins;

  static Key secretKey = MacProvider.generateKey();

  public String extractUsername(String token) throws Exception {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) throws Exception {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws Exception {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) throws Exception {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) throws Exception {
    return extractExpiration(token).before(new Date());
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String subject) {
    final Date createdDate = new Date(System.currentTimeMillis());
    final Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * tokenExpMins);

    return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate)
        .setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secretKey).compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails) {

    try {
      final String username = extractUsername(token);
      return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    } catch (SignatureException ex) {
      LOG.error("Invalid JWT Signature");
    } catch (MalformedJwtException ex) {
      LOG.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      LOG.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      LOG.error("Unsupported JWT exception");
    } catch (IllegalArgumentException ex) {
      LOG.error("Jwt claims string is empty");
    } catch (Exception ex) {
      LOG.error("ex");
    }
    return false;
  }
}

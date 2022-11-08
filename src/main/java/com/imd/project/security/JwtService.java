package com.imd.project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.imd.project.ProjectApplication;
import com.imd.project.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

@Service
public class JwtService {

  @Value("${security.jwt.expiration}")
  private String expiracao;

  @Value("${security.jwt.key-sign}")
  private String chaveAssinatura;

  //

  public String generateToken(User usuario) {
    long expString = Long.valueOf(expiracao);
    LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
    Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
    Date data = Date.from(instant);

    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(chaveAssinatura));

    return Jwts
        .builder()
        .setSubject(usuario.getUsername())
        .setExpiration(data)
        .signWith(key)
        .compact();
  }

  //

  private Claims retrieveClaims(String token) throws ExpiredJwtException {

    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(chaveAssinatura));

    return Jwts
        .parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  //

  public boolean validToken(String token) {
    try {
      Claims claims = (Claims) retrieveClaims(token);

      Date dataExpiracao = claims.getExpiration();

      LocalDateTime data = dataExpiracao
          .toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDateTime();

      return !LocalDateTime.now().isAfter(data);
    } catch (Exception e) {
      return false;
    }
  }

  //

  public String getLoginUser(String token) throws ExpiredJwtException {
    return (String) (retrieveClaims(token)).getSubject();
  }

  //

  public static void main(String[] args) {
    ConfigurableApplicationContext contexto = SpringApplication.run(ProjectApplication.class);
    JwtService service = contexto.getBean(JwtService.class);
    User usuario = User.builder().username("fulano").build();
    System.out.println("Gerando token");
    String token = service.generateToken(usuario);
    System.out.println(token);

    System.out.println("Verifica se o token é válido");
    boolean isTokenValido = service.validToken(token);
    System.out.println("O token é válido? " + isTokenValido);

    System.out.println("Obtenha o login do usuário");
    String login = service.getLoginUser(token);
    System.out.println(login);
  }
}

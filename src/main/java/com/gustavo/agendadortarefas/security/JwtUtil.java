package com.gustavo.agendadortarefas.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;


@Service
public class JwtUtil {

    private final SecretKey secretKey;

    public JwtUtil() {
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Gera uma chave secreta para o algoritmo HS256
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Define o nome de usuário como o assunto do token
                .setIssuedAt(new Date()) // Define a data e hora de emissão do token
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Define a data e hora de expiração (1 hora a partir da emissão)
                .signWith(secretKey) // Converte a chave secreta em bytes e assina o token com ela
                .compact(); // Constrói o token JWT
    }

    public Claims extractClaims(String token) {
        return Jwts.parser() // usa parserBuilder no lugar de parser()
                .setSigningKey(secretKey) // aqui já é um SecretKey, não precisa converter pra bytes
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Extrai o email do usuário do token JWT
    public String extrairEmailToken(String token) {
        // Obtém o assunto (nome de usuário) das claims do token
        return extractClaims(token).getSubject();
    }


    // Extrai o nome de usuário do token JWT
    public String extractUsername(String token) {
        // Obtém o assunto (nome de usuário) das claims do token
        return extractClaims(token).getSubject();
    }

    // Verifica se o token JWT está expirado
    public boolean isTokenExpired(String token) {
        // Compara a data de expiração do token com a data atual
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Valida o token JWT verificando o nome de usuário e se o token não está expirado
    public boolean validateToken(String token, String username) {
        // Extrai o nome de usuário do token
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
package edu.malaka96.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

//    private String secretKey = "";
//
//    public JWTService(){
//        try {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
//            SecretKey sk = keyGenerator.generateKey();
//            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public  String generateToker(String name) {
//
//        Map<String, Object> claims = new HashMap<>();
//
//        return Jwts.builder()
//                .claims()
//                .add(claims)
//                .subject(name)
//                .issuedAt(new Date(System.currentTimeMillis()))
//                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
//                .and()
//                .signWith(getKey())
//                .compact();
//
//    }

    private static final String SECRET_KEY =
            "my-super-secret-key-my-super-secret-key";

    public String generateToken(String name) {
        return Jwts.builder()
                .subject(name)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 30))
                .signWith(getKey())
                .compact();
    }

//    private SecretKey getKey(){
////        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
////        return Keys.hmacShaKeyFor(keyBytes);
//    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}

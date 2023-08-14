package com.skeleton.security.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service    // transform to a managed @Bean of Spring (Inject)
public class JwtService {

    //    @Value("${app.jwt-secret}")
//    private static String SECRET_KEY;
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

//    @Value("${app.jwt-expiration}")
//    private static Long JWT_EXPIRATION_HOURS;
    private static final Long JWT_EXPIRATION_HOURS = 24L;


    public String extractUsername(String jwt) {
        // el Subject deberia ser el   (email,uuid,username)    q vamos a setear en la construccion del JWT
        return extractClaim(jwt, Claims::getSubject);
    }

    // genericos para extraer cualquier claim q nos interese
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);    // extra TODOS los claims del JWT q se le pase
    }


    // // // generate JWT  --  sobrecarga de methods - Polymorphism
    public String generateJwt(Map<String, Object> extraClaims, UserDetails userDetails) {
        // extracalims es lo extra q quiero pasarle al payload del jwt

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())  // payload q se va a codificar (email, uuid, usrname)
                .setIssuedAt(new Date(System.currentTimeMillis()))    // when this jwt was created - to calculate the expiration date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * JWT_EXPIRATION_HOURS))    // setear el tiempo de validez del jwt
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)   //
                .compact();
    }

    // Polymorphism: Without any extraClaims
    public String generateJwt(UserDetails userDetails) {
        return generateJwt(new HashMap<>(), userDetails);
    }


    // // Validate JWT: valida jwt y q el (email,uuid,username) del subject pertenezca a ese usuario <-- en este contexto username=email
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUsername(jwt);

        return (
                username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(jwt)
        );
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey()) // JWT Secret
                .build()    // xq es 1 builder
                .parseClaimsJws(jwt)    // parse el JWT para extraer los claims
                .getBody();     // cuando hace el parse puede obtener los claims y en este caso queremos el body
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);   // jwt lo requiere en b64
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date()); // before 'cause the expiration is th SUM of now and JWT_EXPIRATION_HOURS
    }

}

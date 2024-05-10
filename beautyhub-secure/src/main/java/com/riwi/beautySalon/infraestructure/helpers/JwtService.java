package com.riwi.beautySalon.infraestructure.helpers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.riwi.beautySalon.domain.entities.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
    //Create a variable to save private key(sign)
    private static final String SECRET_KET = "Y2xhdmVTVVBFUnNlY3JldGFTVXBlcnNlY3JldGFVcGVyc2VjcmV0YQ==";
    //Method that will return the encripted key
    public SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KET);
        // return the encripted key
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Method to build our token
    public String getToken(Map<String, Object> claims, User user){

        return Jwts.builder()
                    .claims(claims) //add jwt payload
                    .subject(user.getUsername()) // who is jwt
                    .issuedAt(new Date(System.currentTimeMillis())) // add time
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                    .signWith(this.getKey()) // sign the token
                    .compact();
    }

    // Method to return the token with the configuredted claims
    public String getToken(User user){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("role",user.getRole().name());

        return this.getToken(claims, user);
    }
}

package ch.heigvd.videogames.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.xml.bind.DatatypeConverter;

public class VideoGamesUtils {
    private String secret_key = "22051993_robel_teklehaimanot_amt";

    public void parseJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret_key))
                .parseClaimsJws(jwt).getBody();
        System.out.println("email: " + claims.getSubject());
        System.out.println("Expiration: " + claims.getExpiration());
    }

    public String getMailByToken(String token){
        Claims claims =  Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret_key))
                .parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

}

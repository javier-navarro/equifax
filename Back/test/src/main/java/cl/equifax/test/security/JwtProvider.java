package cl.equifax.test.security;

import cl.equifax.test.entity.Usuarios;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Value("${jwt.secreto}")
    private String secreto;

    @PostConstruct
    protected void init(){
        secreto = Base64.getEncoder().encodeToString(secreto.getBytes());
    }

    public String CreateToken(Usuarios usuarios){

        System.out.println("[createToken] entrada: "+usuarios);
        Map<String,Object> claims = new HashMap<>();
        claims = Jwts.claims().setSubject(usuarios.getUsername());
        claims.put("id", usuarios.getIdUsuarios());
        Date now = new Date();
        Date exp = new Date(now.getTime()+ 360000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256,secreto)
                .compact();
    }

    public boolean validate(String token){
        try{
            Jwts.parser().setSigningKey(secreto).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            System.out.println("en el exception");
            return false;
        }
    }

    public String getUserNameFromToken(String token){
        try{
            return Jwts.parser().setSigningKey(secreto).parseClaimsJws(token).getBody().getSubject();
        }catch (Exception e){
            return "bad token";
        }
    }
}

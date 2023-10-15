package tcc.job.devs.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import tcc.job.devs.payloads.UserPayloads;

import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private static final String JWT_SECRET = "jobdev";

    private static final int JWT_EXPIRY_SECONDS = 999999999;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .claim("id", userPrincipal.getUser().getId())
                .claim("email", userPrincipal.getUser().getEmail())
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRY_SECONDS))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public ObjectNode getDataFromJwtToken(String token) {
        return new ObjectMapper().convertValue(
                Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody(),
                ObjectNode.class
        );
    }

    public int getUserIdFromJWT() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserPayloads.UserModel user = userDetails.getUser();
        return user.getId();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().get("email").toString();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Assinatura do JWT inválida: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("JWT inválido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT expirado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT não suportado: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT é vazio: {}", e.getMessage());
        }
        return false;
    }
}
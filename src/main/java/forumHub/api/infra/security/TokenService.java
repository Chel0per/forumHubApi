package forumHub.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import forumHub.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("forumHubApi")
                .withSubject(usuario.getEmail())
                .withExpiresAt(LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00")))
                .sign(algoritmo);
    }
}

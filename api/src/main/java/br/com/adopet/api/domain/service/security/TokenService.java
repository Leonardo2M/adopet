package br.com.adopet.api.domain.service.security;

import br.com.adopet.api.domain.model.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("VARIAVEL AMBIENTE");
            return JWT.create()
                    .withIssuer("API Adopet")
                    .withSubject(usuario.getUsername())
                    .withClaim("id: ", usuario.getId())
                    .withClaim("nome: ", usuario.getTipo().toString())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}

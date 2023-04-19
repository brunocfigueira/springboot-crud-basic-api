package ms.spring.crudbasic.api.infrastructure.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.http.HttpServletRequest;
import ms.spring.crudbasic.api.domains.users.DetailsUserToken;
import ms.spring.crudbasic.api.domains.users.UserEntity;
import ms.spring.crudbasic.api.infrastructure.configurations.AppConfiguration;
import ms.spring.crudbasic.api.infrastructure.exceptions.ForbiddenAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthTokenService {
    @Autowired
    private AppConfiguration appConfiguration;
    @Autowired
    private AuthService authService;

    @Value("${infra.auth.token.algorithmSecret}")
    private String algorithmSecret;
    @Value("${infra.auth.token.issueName}")
    private String issueName;
    @Value("${infra.auth.token.durationInHours}")
    private long durationInHours;
    @Value("${infra.auth.token.durationInMinutes}")
    private long durationInMinutes;
    @Value("${infra.auth.token.zoneOffsetId}")
    private String zoneOffsetId;
    @Value("${infra.auth.token.audience}")
    private String audience;

    private Instant expirationDate() {

        if (appConfiguration.isEnvironmentProduction()) {
            return LocalDateTime.now().plusHours(durationInHours).toInstant(ZoneOffset.of(zoneOffsetId));
        }
        return LocalDateTime.now().plusMinutes(durationInMinutes).toInstant(ZoneOffset.of(zoneOffsetId));

    }

    private DetailsUserToken loadDetailsTokenByUserId(Long userId) {
        return authService.loadDetailsTokenByUserId(userId);
    }

    private List<String> convertPermissionNamesToArray(String value) {
        return Arrays.asList(value.split(","));
    }

    private List<Long> convertPermissionIdsToArray(String value) {
        var arrayListIds = new ArrayList<Long>();
        for (String str : value.split(",")) {
            arrayListIds.add(Long.parseLong(str));
        }
        return arrayListIds;
    }

    /**
     * sub (subject) = Entidade à quem o token pertence, normalmente o ID do usuário;
     * iss (issuer) = Emissor do token;
     * exp (expiration) = Timestamp de quando o token irá expirar;
     * iat (issued at) = Timestamp de quando o token foi criado;
     * aud (audience) = Destinatário do token, representa a aplicação que irá usá-lo.
     * <p>
     * pwd (password) = senha hash crypto do usuário
     * act (active) = Indicador de status do usuario
     * rol (role) = Indicador do perfil ou papel do usuario
     * pid (permission id) = Indicador dos ids das permissoes do usuario
     * pnm (permission name) = Indicador dos nomes das permissoes do usuario
     */
    public String createTokenUser(UserEntity user) {
        try {
            var credentials = Algorithm.HMAC256(algorithmSecret);
            var detailsUserToken = loadDetailsTokenByUserId(user.getId());
            var permissionIds = convertPermissionIdsToArray(detailsUserToken.getPermissionIds());
            var permissionNames = convertPermissionNamesToArray(detailsUserToken.getPermissionNames());

            return JWT.create()
                    .withIssuedAt(Instant.now())
                    .withIssuer(issueName)
                    .withAudience(audience)
                    .withSubject(user.getLogin())
                    .withClaim("pwd", user.getPassword())
                    .withClaim("act", user.isEnabled())
                    .withClaim("rol", detailsUserToken.getProfileAcronym())
                    .withClaim("pid", permissionIds)
                    .withClaim("pnm", permissionNames)
                    .withExpiresAt(expirationDate())
                    .sign(credentials);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error on create token user", exception);
        }
    }

    public String getSubject(String token) {
        try {
            var credentials = Algorithm.HMAC256(algorithmSecret);
            return JWT.require(credentials)
                    .withIssuer(issueName)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw ForbiddenAccessException.emitMessageWithStack(exception.getMessage(), exception);
        }
    }

    public String getHttpHeaderToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}

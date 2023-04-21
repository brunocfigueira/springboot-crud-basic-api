package ms.spring.crudbasic.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import ms.spring.crudbasic.api.domains.entities.UserEntity;
import ms.spring.crudbasic.api.infrastructure.auth.AuthRequestDto;
import ms.spring.crudbasic.api.infrastructure.auth.AuthResponseTokenDto;
import ms.spring.crudbasic.api.infrastructure.auth.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private AuthTokenService authTokenService;

    @Operation(summary = "Request Authentication Token", description = "Execute operation of get user token")
    @PostMapping("/token")
    public ResponseEntity requestAuthToken(@RequestBody @Valid AuthRequestDto request) {
        var authToken = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        var auth = authManager.authenticate(authToken);

        var tokenJWT = authTokenService.createTokenUser((UserEntity) auth.getPrincipal());
        return ResponseEntity.ok(new AuthResponseTokenDto(tokenJWT));
    }
}

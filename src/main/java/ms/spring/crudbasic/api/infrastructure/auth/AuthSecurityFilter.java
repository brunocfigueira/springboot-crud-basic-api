package ms.spring.crudbasic.api.infrastructure.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ms.spring.crudbasic.api.infrastructure.exceptions.ForbiddenAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthSecurityFilter extends OncePerRequestFilter {
    @Autowired
    private AuthTokenService authTokenService;
    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var login = getUserLoginThroughSubject(request);
        if (login != null) {
            var user = getUser(login);
            var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private UserDetails getUser(String login) {

        var user = authService.loadUserByLogin(login);
        if (!user.isEnabled()) {
            throw ForbiddenAccessException.emitMessage("Invalid credentials.");
        }
        return user;
    }

    private String getUserLoginThroughSubject(HttpServletRequest request) {
        var tokenJWT = authTokenService.getHttpHeaderToken(request);
        if (tokenJWT != null) {
            return authTokenService.getSubject(tokenJWT);
        }
        return null;
    }
}

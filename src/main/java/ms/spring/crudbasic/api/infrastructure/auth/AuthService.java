package ms.spring.crudbasic.api.infrastructure.auth;

import ms.spring.crudbasic.api.domains.users.DetailsUserToken;
import ms.spring.crudbasic.api.domains.users.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class AuthService implements UserDetailsService {
    @Autowired
    private IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLoginAndActiveIsTrue(login);
    }

    public UserDetails loadUserByLogin(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }

    public DetailsUserToken loadDetailsTokenByUserId(Long userId) {
        return repository.findDetailsTokenByUserId(userId);
    }
}

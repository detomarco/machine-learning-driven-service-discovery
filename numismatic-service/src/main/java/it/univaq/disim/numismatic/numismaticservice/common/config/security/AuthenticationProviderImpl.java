package it.univaq.disim.numismatic.numismaticservice.common.config.security;

import it.univaq.disim.numismatic.numismaticservice.business.UserService;
import it.univaq.disim.numismatic.numismaticservice.domain.User;
import it.univaq.disim.numismatic.numismaticservice.business.ws.ServiceDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private final ServiceDispatcher serviceDispatcher;

    private final UserService userService;

    @Autowired
    public AuthenticationProviderImpl(ServiceDispatcher serviceDispatcher, UserService userService) {
        this.serviceDispatcher = serviceDispatcher;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {

            // perform login through web service
            User user = serviceDispatcher.login(username, password);
            // refresh user in local database
            userService.updateUser(user);

            return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

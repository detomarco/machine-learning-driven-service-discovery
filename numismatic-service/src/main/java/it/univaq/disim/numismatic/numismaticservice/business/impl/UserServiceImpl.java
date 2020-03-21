package it.univaq.disim.numismatic.numismaticservice.business.impl;

import it.univaq.disim.numismatic.numismaticservice.business.UserService;
import it.univaq.disim.numismatic.numismaticservice.business.ws.ServiceDispatcher;
import it.univaq.disim.numismatic.numismaticservice.common.mapper.ModelMapper;
import it.univaq.disim.numismatic.numismaticservice.domain.Authority;
import it.univaq.disim.numismatic.numismaticservice.domain.User;
import it.univaq.disim.numismatic.numismaticservice.repository.AuthorityRepository;
import it.univaq.disim.numismatic.numismaticservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private ServiceDispatcher serviceDispatcher;

    @Override
    @Transactional
    public void updateUser(User user) {
        // find cached user
        Optional<User> userCachedOpt = userRepository.findByUsername(user.getUsername());
        // if does not exists
        if (!userCachedOpt.isPresent()) {
            // save it
            userRepository.save(user);
        } else { // otherwise
            // refresh data
            User userRefreshed = ModelMapper.merge(userCachedOpt.get(), user);
            user.setAuthorities(retrieveAuthoritiesCached(user));
            userRepository.save(userRefreshed);
        }

    }

    @Override
    public List<User> nearbyUsers(String username, Double radius) {
        // retrieve nearby users through web service
        return serviceDispatcher.getNearbyUsers(username, radius);
    }

    private List<Authority> retrieveAuthoritiesCached(User user) {
        List<Authority> authoritiesCached = authorityRepository.findAll();
        // retrieve authorities from db if exists
        List<Authority> userAuthorities = new ArrayList<>();
        user.getAuthorities()
                .forEach(authority -> {
                    Optional<Authority> authorityOpt = authoritiesCached.stream()
                            .filter(authorityCached -> authorityCached.equals(authority))
                            .findFirst();
                    if (authorityOpt.isPresent()) {
                        userAuthorities.add(authorityOpt.get());
                    } else {
                        userAuthorities.add(authority);
                    }
                });
        return userAuthorities;
    }

}

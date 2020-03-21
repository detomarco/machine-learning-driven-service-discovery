package it.univaq.disim.numismatic.numismaticservice.business.impl;

import it.univaq.disim.numismatic.numismaticservice.business.CountryService;
import it.univaq.disim.numismatic.numismaticservice.domain.CoinField;
import it.univaq.disim.numismatic.numismaticservice.domain.User;
import it.univaq.disim.numismatic.numismaticservice.repository.CoinRepository;
import it.univaq.disim.numismatic.numismaticservice.repository.UserRepository;
import it.univaq.disim.numismatic.numismaticservice.business.ws.ServiceDispatcher;
import it.univaq.disim.numismatic.numismaticservice.common.exception.BusinessException;
import it.univaq.disim.numismatic.numismaticservice.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private final ServiceDispatcher serviceDispatcher;

    private final CoinRepository coinRepository;

    private final UserRepository userRepository;

    @Autowired
    public CountryServiceImpl(ServiceDispatcher serviceDispatcher, CoinRepository coinRepository, UserRepository userRepository) {
        this.serviceDispatcher = serviceDispatcher;
        this.coinRepository = coinRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<String> retrieveCountries() {
        // retrieve countries through web service
        return serviceDispatcher.retrieveFieldValues(CoinField.COUNTRY).stream()
                .map(value -> value.getValue().toString())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> retrieveUserCountries(String username) {
        // check user
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, username);
        }

        return coinRepository.findCountriesByUsername(username);
    }

}

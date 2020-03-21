package it.univaq.disim.numismatic.numismaticservice.business.impl;

import it.univaq.disim.numismatic.numismaticservice.business.CoinService;
import it.univaq.disim.numismatic.numismaticservice.business.ws.ServiceDispatcher;
import it.univaq.disim.numismatic.numismaticservice.common.exception.BusinessException;
import it.univaq.disim.numismatic.numismaticservice.common.exception.ErrorCode;
import it.univaq.disim.numismatic.numismaticservice.domain.Coin;
import it.univaq.disim.numismatic.numismaticservice.domain.CoinField;
import it.univaq.disim.numismatic.numismaticservice.domain.User;
import it.univaq.disim.numismatic.numismaticservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoinServiceImpl implements CoinService {

    @Autowired
    private ServiceDispatcher serviceDispatcher;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Coin> retrieveCoinsByExample(String username, Coin example, List<CoinField> fields) {

        // check user
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, username);
        }

        // retrieve coins by example through web service
        List<Coin> coins = serviceDispatcher.retrieveCoinsByExample(example, fields);

        // retrieve user's coins that match with example
        List<Coin> userCoins = userOpt.get().getCoins().stream()
                .filter(coin1 -> match(coin1, example))
                .collect(Collectors.toList());

        // merge retrieved coins with user's coins
        return coins.stream()
                .peek(coin ->
                        userCoins.stream()
                                .filter(userCoin -> userCoin.equals(coin))
                                .forEach(userCoin -> coin.setId(userCoin.getId()))
                ).collect(Collectors.toList());
    }

    @Override
    public List<Coin> retrieveUserCoins(String username, Coin example, List<CoinField> fields) {

        // check user
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, username);
        }

        User user = userOpt.get();

        // retrieve user's coins that match with example
        List<Coin> userCoins = user.getCoins().stream()
                .filter(coin1 -> match(coin1, example))
                .collect(Collectors.toList());

        // if there are not user coins
        if (userCoins.isEmpty()) { // retrieve empty list
            return new ArrayList<>();
        } else {

            // retrieve coin information throw web service
            List<Coin> coins = serviceDispatcher.retrieveCoinsByExample(example, fields);
            // merge retrieved coins with user's coins
            return coins.stream()
                    .filter(userCoins::contains)
                    .peek(coin ->
                            userCoins.stream()
                                    .filter(userCoin -> userCoin.equals(coin))
                                    .forEach(userCoin -> coin.setId(userCoin.getId())))
                    .collect(Collectors.toList());
        }
    }

    // check if two coins have the same properties value
    private boolean match(Coin c1, Coin c2) {
        if (c2.getCountry() != null && !c2.getCountry().equals(c1.getCountry())) return false;
        if (c2.getSeries() != null && !c2.getSeries().equals(c1.getCountry())) return false;
        if (c2.getType() != null && !c2.getType().equals(c1.getType())) return false;
        if (c2.getYearFrom() != null && !c2.getYearFrom().equals(c1.getYearFrom())) return false;
        if (c2.getValue() != null && !c2.getValue().equals(c1.getValue())) return false;
        return true;
    }


}

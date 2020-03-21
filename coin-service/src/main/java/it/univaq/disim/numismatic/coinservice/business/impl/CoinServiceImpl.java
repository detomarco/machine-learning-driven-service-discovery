package it.univaq.disim.numismatic.coinservice.business.impl;

import it.univaq.disim.numismatic.coinservice.business.CoinService;
import it.univaq.disim.numismatic.coinservice.business.domain.Coin;
import it.univaq.disim.numismatic.coinservice.business.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CoinServiceImpl implements CoinService {

    @Autowired
    private CoinRepository coinRepository;

    @Override
    public List<Coin> retrieveCoinsByExample(Coin example) {
        return coinRepository.retrieveCoinsByExample(example);
    }

}

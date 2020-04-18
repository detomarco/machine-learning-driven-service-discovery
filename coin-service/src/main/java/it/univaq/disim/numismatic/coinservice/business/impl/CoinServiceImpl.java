package it.univaq.disim.numismatic.coinservice.business.impl;

import it.univaq.disim.discovery.common.DiscoveryRestTemplate;
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

    @Autowired
    private DiscoveryRestTemplate discoveryRestTemplate;

    @Override
    public List<Coin> retrieveCoinsByExample(Coin example) {
        discoveryRestTemplate.get("stub-service", "/stub", Object.class);

        return coinRepository.retrieveCoinsByExample(example);
    }

}

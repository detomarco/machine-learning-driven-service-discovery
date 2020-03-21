package it.univaq.disim.numismatic.numismaticservice.business.ws;

import it.univaq.disim.discovery.common.DiscoveryRestTemplate;
import it.univaq.disim.numismatic.numismaticservice.client.model.CoinFieldType;
import it.univaq.disim.numismatic.numismaticservice.client.model.LoginRequest;
import it.univaq.disim.numismatic.numismaticservice.client.model.LoginResponse;
import it.univaq.disim.numismatic.numismaticservice.client.model.NearbyUsersByUserResponse;
import it.univaq.disim.numismatic.numismaticservice.client.model.RetrieveCoinsByRequest;
import it.univaq.disim.numismatic.numismaticservice.client.model.RetrieveFieldValuesRequest;
import it.univaq.disim.numismatic.numismaticservice.client.model.RetrieveFieldValuesResponse;
import it.univaq.disim.numismatic.numismaticservice.controller.model.CoinsListResponse;
import it.univaq.disim.numismatic.numismaticservice.domain.Coin;
import it.univaq.disim.numismatic.numismaticservice.domain.CoinField;
import it.univaq.disim.numismatic.numismaticservice.domain.CoinFieldValue;
import it.univaq.disim.numismatic.numismaticservice.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ServiceDispatcher {

    @Autowired
    private DiscoveryRestTemplate discoveryRestTemplate;

    public User login(String username, String password) {
        // create request
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        LoginResponse response = discoveryRestTemplate.post("user-service", "/login", request, LoginResponse.class);
        return ServiceMapper.toModel(response.getUser());
    }

    public List<User> getNearbyUsers(String username, Double radius) {
        NearbyUsersByUserResponse response = discoveryRestTemplate.get("user-service", "/nearbyuser?username=" + username + "&radius=" + radius, NearbyUsersByUserResponse.class);
        return ServiceMapper.toUserModelList(response.getUsers());
    }

    public List<Coin> retrieveCoinsByExample(Coin coin, List<CoinField> fields) {
        // create request
        RetrieveCoinsByRequest request = new RetrieveCoinsByRequest();
        request.setExample(ServiceMapper.toType(coin));
        request.setFields(ServiceMapper.toCoinFieldTypeList(fields));
        CoinsListResponse response = discoveryRestTemplate.post("coin-service", "/retrieveBy", request, CoinsListResponse.class);
        return ServiceMapper.toCoinRestModelList(response.getCoins());
    }

    public List<CoinFieldValue> retrieveFieldValues(CoinField field) {
        // create request
        RetrieveFieldValuesRequest request = new RetrieveFieldValuesRequest();
        request.setField(CoinFieldType.fromValue(field.value()));
        RetrieveFieldValuesResponse response = discoveryRestTemplate.post("coin-service", "/retrieveFields", request, RetrieveFieldValuesResponse.class);
        return ServiceMapper.toCoinFieldValuesModel(response.getValues());
    }

}

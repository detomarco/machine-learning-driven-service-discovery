package it.univaq.disim.numismatic.numismaticservice.repository;

import it.univaq.disim.numismatic.numismaticservice.domain.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

    @Query("SELECT DISTINCT c.country " +
            "FROM User u LEFT JOIN u.coins c " +
            "WHERE u.username = :username")
    List<String> findCountriesByUsername(String username);

}

package it.univaq.disim.numismatic.coinservice.business.repository.impl;

import it.univaq.disim.numismatic.coinservice.business.domain.Coin;
import it.univaq.disim.numismatic.coinservice.business.repository.CoinRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
class CoinRepositoryImpl extends BaseRepositoryImpl<Coin> implements CoinRepository {

    @Override
    public List<Coin> retrieveCoinsByExample(Coin example) {

        // if there is not an example
        if (example == null) {
            // retrieve all
            return findAll();
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Coin> cq = cb.createQuery(Coin.class);


        Root<Coin> coin = cq.from(Coin.class);

        // WHERE
        List<Predicate> where = new ArrayList<>();
        if (example.getCountry() != null) {
            where.add(cb.equal(coin.get("country"), example.getCountry()));
        }
        if (example.getType() != null) {
            where.add(cb.equal(coin.get("type"), example.getType()));
        }
        if (example.getValue() != null) {
            where.add(cb.equal(coin.get("value"), example.getValue()));
        }
        if (example.getSeries() != null) {
            where.add(cb.equal(coin.get("series"), example.getSeries()));
        }
        if (example.getYearFrom() != null) {
            where.add(cb.equal(coin.get("yearFrom"), example.getYearFrom()));
        }
        if (example.getYearTo() != null) {
            where.add(cb.equal(coin.get("yearTo"), example.getYearTo()));
        }
        if (example.getDiameter() != null) {
            where.add(cb.equal(coin.get("diameter"), example.getDiameter()));
        }
        if (example.getThickness() != null) {
            where.add(cb.equal(coin.get("thickness"), example.getThickness()));
        }
        if (example.getWeight() != null) {
            where.add(cb.equal(coin.get("weight"), example.getWeight()));
        }

        Predicate[] predicatesArray = new Predicate[where.size()];
        for (int i = 0; i < where.size(); i++) {
            predicatesArray[i] = where.get(i);
        }
        cq.where(predicatesArray);

        TypedQuery<Coin> query = entityManager.createQuery(cq);
        return query.getResultList();

    }

}

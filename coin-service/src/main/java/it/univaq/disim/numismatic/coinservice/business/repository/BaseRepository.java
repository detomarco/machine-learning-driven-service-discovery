package it.univaq.disim.numismatic.coinservice.business.repository;

import java.util.List;

public interface BaseRepository<T> {

	List<T> findAll();

}

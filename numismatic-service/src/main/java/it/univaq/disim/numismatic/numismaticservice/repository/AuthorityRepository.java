package it.univaq.disim.numismatic.numismaticservice.repository;

import it.univaq.disim.numismatic.numismaticservice.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}

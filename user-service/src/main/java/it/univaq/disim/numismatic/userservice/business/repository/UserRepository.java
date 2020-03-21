package it.univaq.disim.numismatic.userservice.business.repository;

import it.univaq.disim.numismatic.userservice.business.domain.User;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    @Query("FROM User user WHERE within(user.city.point, :circle) = true")
    List<User> findNearbyCities(Geometry circle);

}

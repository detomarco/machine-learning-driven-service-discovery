package it.univaq.disim.numismatic.stubservice.business.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.locationtech.jts.geom.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@ToString(exclude = {"users"})
@JsonIgnoreProperties({"users"})
@EqualsAndHashCode(of = "name", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cities")
public class City extends BaseEntity {

    private static final long serialVersionUID = 1900721827483985497L;

    @Column(unique = true)
    private String name;

    private Point point;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<User> users;

}

package it.univaq.disim.numismatic.numismaticservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@ToString(exclude = {"password", "coins"})
@JsonIgnoreProperties({"password", "coins"})
@EqualsAndHashCode(of = "username", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private static final long serialVersionUID = -6002425382235523602L;

    @Column(unique = true, length = 20, nullable = false)
    private String username;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Authority> authorities;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Coin> coins;

    @Transient
    private String password;
    @Transient
    private String email;
    @Transient
    private String name;
    @Transient
    private String surname;
    @Transient
    private City city;
}

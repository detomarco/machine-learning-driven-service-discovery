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
import javax.persistence.UniqueConstraint;
import java.util.List;

@Data
@ToString(exclude = {"image", "users"})
@JsonIgnoreProperties({"image", "users"})
@EqualsAndHashCode(of = {"value", "type", "country", "series", "yearFrom"}, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "coins",
        uniqueConstraints = @UniqueConstraint(columnNames = {"value", "type", "country", "series", "yearFrom"}))
public class Coin extends BaseEntity {

    private static final long serialVersionUID = -2666802590662871751L;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String series;

    private Integer yearFrom;

    @ManyToMany(mappedBy = "coins", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users;

    // transient values
    @Transient
    protected Integer yearTo;
    @Transient
    protected Double diameter;
    @Transient
    protected Double thickness;
    @Transient
    protected Double weight;
    @Transient
    protected byte[] image;

}

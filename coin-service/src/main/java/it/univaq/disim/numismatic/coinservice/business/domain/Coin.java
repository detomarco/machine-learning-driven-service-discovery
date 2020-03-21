package it.univaq.disim.numismatic.coinservice.business.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@ToString(exclude = {"image"})
@EqualsAndHashCode(of = {"value", "type", "country", "series", "yearFrom"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "coins",
        uniqueConstraints = @UniqueConstraint(columnNames = {"value", "type", "country", "series", "yearFrom"}))
public class Coin {

    private static final long serialVersionUID = -5315494624835486529L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private String type;

    private String country;

    @Column(nullable = false)
    private String series;

    @Column(nullable = false)
    private Integer yearFrom;

    private Integer yearTo;
    private Double diameter;
    private Double thickness;
    private Double weight;

    @Lob
    private byte[] image;

}

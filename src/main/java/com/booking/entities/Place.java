package com.booking.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "PLACE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLACE_ID_SEQ")
  @SequenceGenerator(name = "PLACE_ID_SEQ", sequenceName = "PLACE_ID_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "CITY")
  private String city;

  @Column(name = "STATE")
  private String state;

  @Column(name = "ZIPCODE")
  private String zipCode;

  @Column(name = "COUNTRY")
  private String country;

  @JsonManagedReference
  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Theatre> theatreSet;

}

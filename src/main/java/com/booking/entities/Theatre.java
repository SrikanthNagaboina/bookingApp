package com.booking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "THEATRE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theatre implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "THEATRE_ID_SEQ")
  @SequenceGenerator(name = "THEATRE_ID_SEQ", sequenceName = "THEATRE_ID_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "TOTAL_CINEMA_HALLS")
  private Integer totalCinemaHalls;

  @JsonBackReference
  @JoinColumn(name = "PLACE_ID", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Place place;

  @JsonManagedReference
  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(mappedBy = "theatre", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Screen> screenSet;

}

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
@Table(name = "SCREEN")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Screen implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCREEN_ID_SEQ")
  @SequenceGenerator(name = "SCREEN_ID_SEQ", sequenceName = "SCREEN_ID_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "TOTAL_SEATS")
  private Integer totalSeats;

  @Column(name = "AVAILABLE_SEATS")
  private Integer availableSeats;

  @JsonBackReference
  @JoinColumn(name = "THEATRE_ID", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Theatre theatre;

  @JsonBackReference
  @JoinColumn(name = "MOVIE_ID", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Movie movie;

  @JsonManagedReference
  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(mappedBy = "screen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Tier> tierSet;

  @JsonManagedReference
  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(mappedBy = "screen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Show> shows;


}

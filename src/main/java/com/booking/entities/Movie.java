package com.booking.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "MOVIE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVIE_ID_SEQ")
  @SequenceGenerator(name = "MOVIE_ID_SEQ", sequenceName = "MOVIE_ID_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "DESCRIPTION", length = 512)
  private String description;

  @Column(name = "LANGUAGE")
  private String language;

  @Column(name = "DURATION")
  private Date duration;

  @Column(name = "RELEASE_DATE")
  private Date releaseDate;

  @Column(name = "COUNTRY")
  private String country;

  @Column(name = "GENRE")
  private String genre;

  @JsonManagedReference
  @LazyToOne(LazyToOneOption.PROXY)
  @OneToOne(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Screen screen;

  @JsonManagedReference
  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Show> shows;


}

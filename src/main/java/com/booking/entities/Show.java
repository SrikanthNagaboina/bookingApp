package com.booking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SHOW")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOW_ID_SEQ")
  @SequenceGenerator(name = "SHOW_ID_SEQ", sequenceName = "SHOW_ID_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "DATE")
  private Date date;

  @Column(name = "START_TIME")
  private Date startTime;

  @Column(name = "END_TIME")
  private Date endTime;

  @JsonBackReference
  @JoinColumn(name = "SCREEN_ID", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Screen screen;

  @JsonBackReference
  @JoinColumn(name = "MOVIE_ID", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Movie movie;

}

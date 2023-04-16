package com.booking.entities;

import com.booking.commons.SeatType;
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
@Table(name = "TIER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tier implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIER_ID_SEQ")
  @SequenceGenerator(name = "TIER_ID_SEQ", sequenceName = "TIER_ID_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "TYPE")
  private SeatType seatType;

  @JsonBackReference
  @JoinColumn(name = "SCREEN_ID", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Screen screen;

  @JsonManagedReference
  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(mappedBy = "tier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Seat> seatSet;

}

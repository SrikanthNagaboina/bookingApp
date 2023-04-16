package com.booking.entities;

import com.booking.commons.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SEAT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEAT_ID_SEQ")
  @SequenceGenerator(name = "SEAT_ID_SEQ", sequenceName = "SEAT_ID_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "STATUS")
  private Status status;

  @Column(name = "PRICE")
  private Float price;

  @JsonBackReference
  @JoinColumn(name = "TIER_ID", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Tier tier;

  @JsonBackReference
  @JoinColumn(name = "TICKET_ID", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private Ticket ticket;

}

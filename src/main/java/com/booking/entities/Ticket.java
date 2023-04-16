package com.booking.entities;

import com.booking.commons.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "TICKET")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TICKET_ID_SEQ")
  @SequenceGenerator(name = "TICKET_ID_SEQ", sequenceName = "TICKET_ID_SEQ", allocationSize = 1)
  private Long id;

  @Column(name = "NUMBER_OF_SEATS")
  private Integer numOfSeats;

  @Column(name = "TIMESTAMP")
  private Date timestamp;

  @Column(name = "STATUS")
  private Status status;

  @JsonBackReference
  @JoinColumn(name = "USER_ID", referencedColumnName = "id")
  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  private User user;

  @JsonManagedReference
  @LazyCollection(LazyCollectionOption.TRUE)
  @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Seat> seatSet;


}

package com.booking.controllers;

import com.booking.beans.TicketRequestBean;
import com.booking.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

  @Autowired
  private TicketService ticketService;

  @PostMapping(path = "/bookTicket")
  public ResponseEntity<?> bookTicket(@RequestBody TicketRequestBean requestBean) {
    try {
      return ResponseEntity.ok(ticketService.bookTicket(requestBean));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}

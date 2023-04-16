package com.booking.services;

import com.booking.beans.TicketRequestBean;
import com.booking.commons.SeatType;
import com.booking.commons.Status;
import com.booking.entities.*;
import com.booking.repositories.ScreenRepository;
import com.booking.repositories.TheatreRepository;
import com.booking.repositories.TicketRepository;
import com.booking.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private TheatreRepository theatreRepository;

  @Autowired
  private ScreenRepository screenRepository;

  @Autowired
  private UserRepository userRepository;

  @Transactional
  public Ticket bookTicket(TicketRequestBean bean) throws Exception {
    try {
      String screenName = bean.getScreenName();
      Date showTime = bean.getShowTime();
      SeatType seatType = bean.getSeatType();
      List<Long> seatNumbers = bean.getSeatNumbers();
      Long userId = bean.getUserId();
      if (null != screenName && !screenName.isEmpty() &&
          null != showTime && showTime.after(new Date()) &&
          null != seatNumbers && !seatNumbers.isEmpty()) {
        Optional<Screen> screenOptional = screenRepository.findByNameAndShowsStartTime(screenName, showTime);
        Optional<User> userOptional = userRepository.findById(userId);
        if (screenOptional.isEmpty())
          throw new Exception("Selected screen does not exists");
        Screen screen = screenOptional.get();
        if (null != screen.getTierSet() && !screen.getTierSet().isEmpty() && null != screen.getShows() && !screen.getShows().isEmpty()) {
          Set<Seat> seatSet = screen.getTierSet().stream()
              .filter(tier -> tier.getSeatType().equals(seatType))
              .map(Tier::getSeatSet)
              .flatMap(Set::stream)
              .filter(seat -> (seatNumbers.contains(seat.getId()) && !Status.Reserved.equals(seat.getStatus())))
              .collect(Collectors.toSet());
          if (seatSet.size() != seatNumbers.size())
            throw new Exception("Selected seats are not available, please try with another option");
          Optional<Show> showOptional = screen.getShows().stream().filter(show -> show.getStartTime().equals(showTime)).findFirst();
          if (showOptional.isEmpty())
            throw new Exception("Screen with selected time does not available to book");
          Ticket ticket = new Ticket(null, seatSet.size(), new Date(), Status.Booked, userOptional.orElseThrow(), seatSet);
          ticket = ticketRepository.save(ticket);
          return ticket;
        }
      }
    } catch (Exception e) {
      log.error("Exception occurred while booking the ticket {}", e.getMessage());
      throw e;
    }
    return null;
  }
}

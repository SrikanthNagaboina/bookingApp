package com.booking.beans;

import com.booking.commons.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestBean {

  private String screenName;

  private Date showTime;

  private SeatType seatType;

  private List<Long> seatNumbers;

  private Long userId;

}

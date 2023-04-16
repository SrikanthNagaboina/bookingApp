package com.booking.services;

import com.booking.entities.Theatre;
import com.booking.repositories.TheatreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TheatreService {

  private final TheatreRepository theatreRepository;

  public TheatreService(TheatreRepository theatreRepository) {
    this.theatreRepository = theatreRepository;
  }

  public List<Theatre> findAll() {
    return theatreRepository.findAll();
  }

  @Transactional
  public List<Theatre> findTheatreByCityAndMovieAndShowTime(String city, String movie, Date movieDate, Date showTime) {
    return theatreRepository.findTheatreByCityAndMovieAndShowTime(city, movie, movieDate, showTime);
  }
}

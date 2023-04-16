package com.booking.services;

import com.booking.entities.Movie;
import com.booking.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

  @Autowired
  private MovieRepository movieRepository;

  public List<Movie> findAll() {
    return movieRepository.findAll();
  }

}

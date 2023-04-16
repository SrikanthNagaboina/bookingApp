package com.booking.controllers;

import com.booking.entities.Theatre;
import com.booking.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class TheatreController {

  @Autowired
  private TheatreService theatreService;

  @GetMapping(path = "/theatreByCityAndMovie")
  public ResponseEntity<List<Theatre>> findTheatreByCityAndMovie(@RequestParam(name = "city") String city,
                                                                 @RequestParam(name = "movie") String movie,
                                                                 @RequestParam(name = "movieDate") Date movieDate,
                                                                 @RequestParam(name = "showTime") Date showTime) {
    List<Theatre> theatres = theatreService.findTheatreByCityAndMovieAndShowTime(city, movie, movieDate, showTime);
    if (null != theatres && !theatres.isEmpty()) {
      return ResponseEntity.ok(theatres);
    }
    return ResponseEntity.noContent().build();
  }
}

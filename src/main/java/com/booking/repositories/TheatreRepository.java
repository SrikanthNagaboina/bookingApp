package com.booking.repositories;

import com.booking.entities.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long>, JpaSpecificationExecutor<Theatre> {

  Optional<Theatre> findByName(String name);

  @Query(value = "SELECT TH.* " +
      "FROM THEATRE TH " +
      "INNER JOIN PLACE P ON P.ID=TH.PLACE_ID AND P.CITY=?1 " +
      "INNER JOIN SCREEN S ON S.THEATRE_ID = TH.ID " +
      "INNER JOIN MOVIE M ON M.ID = S.MOVIE_ID AND  M.TITLE=?2 " +
      "INNER JOIN SHOW SH ON SH.MOVIE_ID = M.ID AND SH.DATE > ?3 AND SH.START_TIME =?4", nativeQuery = true)
  List<Theatre> findTheatreByCityAndMovieAndShowTime(String city, String movie, Date movieDate, Date showTime);

}

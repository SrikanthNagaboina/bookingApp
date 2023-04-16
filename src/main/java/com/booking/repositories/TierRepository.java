package com.booking.repositories;

import com.booking.entities.Tier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TierRepository extends JpaRepository<Tier, Long>, JpaSpecificationExecutor<Tier> {

  List<Tier> findByName(String name);

}

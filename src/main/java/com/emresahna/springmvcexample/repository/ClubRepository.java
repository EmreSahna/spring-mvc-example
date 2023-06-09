package com.emresahna.springmvcexample.repository;

import com.emresahna.springmvcexample.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Long> {
    Optional<Club> findByTitle(String title);
}

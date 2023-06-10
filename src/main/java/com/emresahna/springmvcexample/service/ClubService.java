package com.emresahna.springmvcexample.service;

import com.emresahna.springmvcexample.dto.ClubDto;
import com.emresahna.springmvcexample.model.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAll();
    void saveClub(ClubDto club);
    ClubDto findClubDtoById(Long id);
    Club findById(Long id);
    void updateClub(ClubDto club);
    void deleteClub(Long id);
    List<ClubDto> searchClubs(String query);
}

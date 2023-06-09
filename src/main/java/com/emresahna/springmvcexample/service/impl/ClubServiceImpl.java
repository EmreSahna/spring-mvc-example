package com.emresahna.springmvcexample.service.impl;

import com.emresahna.springmvcexample.dto.ClubDto;
import com.emresahna.springmvcexample.mapper.ClubMapper;
import com.emresahna.springmvcexample.model.Club;
import com.emresahna.springmvcexample.model.User;
import com.emresahna.springmvcexample.repository.ClubRepository;
import com.emresahna.springmvcexample.security.SecurityUtil;
import com.emresahna.springmvcexample.service.ClubService;
import com.emresahna.springmvcexample.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.emresahna.springmvcexample.mapper.ClubMapper.mapToClub;
import static com.emresahna.springmvcexample.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;
    private final UserService userService;

    public ClubServiceImpl(ClubRepository clubRepository, UserService userService) {
        this.clubRepository = clubRepository;
        this.userService = userService;
    }

    @Override
    public List<ClubDto> findAll() {
        return clubRepository.findAll().stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public void saveClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        Authentication authentication = SecurityUtil.getSession();
        User user = userService.findByUsername(authentication.getName());
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public ClubDto findClubDtoById(Long id) {
        Club club = findById(id);
        return mapToClubDto(club);
    }

    @Override
    public Club findById(Long id) {
        return clubRepository.findById(id).get();
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
    }

    @Override
    public void deleteClub(Long id) {
        clubRepository.deleteById(id);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        return clubRepository.searchClubs(query).stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }
}

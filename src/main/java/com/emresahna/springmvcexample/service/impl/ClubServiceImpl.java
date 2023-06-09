package com.emresahna.springmvcexample.service.impl;

import com.emresahna.springmvcexample.dto.ClubDto;
import com.emresahna.springmvcexample.model.Club;
import com.emresahna.springmvcexample.repository.ClubRepository;
import com.emresahna.springmvcexample.service.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService {
    private final ClubRepository clubRepository;

    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAll() {
        return clubRepository.findAll().stream().map(this::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public void saveClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
    }

    private ClubDto mapToClubDto(Club club){
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .imageUrl(club.getImageUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .build();
    }

    @Override
    public ClubDto findById(Long id) {
        Club club = clubRepository.findById(id).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
        clubRepository.save(club);
    }

    private Club mapToClub(ClubDto clubDto){
        return Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .imageUrl(clubDto.getImageUrl())
                .content(clubDto.getContent())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();
    }
}

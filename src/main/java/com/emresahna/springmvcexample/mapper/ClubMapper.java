package com.emresahna.springmvcexample.mapper;

import com.emresahna.springmvcexample.dto.ClubDto;
import com.emresahna.springmvcexample.model.Club;

import java.util.stream.Collectors;

public class ClubMapper {
    public static ClubDto mapToClubDto(Club club){
        return ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .imageUrl(club.getImageUrl())
                .content(club.getContent())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .events(club.getEvents().stream().map(EventMapper::mapToEventDto).collect(Collectors.toList()))
                .build();
    }

    public static Club mapToClub(ClubDto clubDto){
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

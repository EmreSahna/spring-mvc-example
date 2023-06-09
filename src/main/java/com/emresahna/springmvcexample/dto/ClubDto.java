package com.emresahna.springmvcexample.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ClubDto {
    private Long id;
    @NotEmpty(message = "Title cannot be null")
    private String title;
    @NotEmpty(message = "Image URL cannot be null")
    private String imageUrl;
    @NotEmpty(message = "Content cannot be null")
    private String content;
    private Timestamp createdOn;
    private Timestamp updatedOn;
}

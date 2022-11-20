package com.intervale.crud.service.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MediaDto {
    private String type;
    private String name;
    private String author;
    private String themes;
    private String publishingHouse;
    private Long printDate;
    private String identifier;
}

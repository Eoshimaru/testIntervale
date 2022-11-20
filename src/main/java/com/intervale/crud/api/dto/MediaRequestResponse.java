package com.intervale.crud.api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MediaRequestResponse {
    private String type;
    private String name;
    private String author;
    private String themes;
    private String publishingHouse;
    private Long printDate;
    private String identifier;
}
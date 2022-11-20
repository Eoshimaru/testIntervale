package com.intervale.crud.repository.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;

@Builder
@Data
public class Media {
    @Id
    private Long id;
    private String type;
    private String name;
    private String author;
    private String themes;
    private String publishingHouse;
    private Timestamp printDate;
    private String identifier;
}

package com.intervale.crud.service;

import com.intervale.crud.service.dto.MediaDto;

import java.sql.Timestamp;
import java.util.List;

public interface MediaService {

    void save(MediaDto mediaDto);

    List<MediaDto> getAll(String author, String themes, String publishingHouse, Long startDate, Long endDate, String type);

    MediaDto getMedia(String identifier);

    void delete(String identifier);

    void update(MediaDto mediaDto);

}

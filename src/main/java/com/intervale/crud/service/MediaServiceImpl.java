package com.intervale.crud.service;

import com.intervale.crud.repository.entity.Media;
import com.intervale.crud.repository.MediaRepository;
import com.intervale.crud.service.dto.MediaDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;

    @Override
    public void save(MediaDto media) {

        mediaRepository.save(Media.builder()
                .type(media.getType())
                .name(media.getName())
                .author(media.getAuthor())
                .themes(media.getThemes())
                .publishingHouse(media.getPublishingHouse())
                .printDate(new Timestamp(media.getPrintDate()))
                .identifier(UUID.randomUUID().toString())
                .build());
    }

    @Override
    public List<MediaDto> getAll(String author, String themes, String publishingHouse, Long startDate, Long endDate, String type) {

        return mediaRepository.findAll(author, themes, publishingHouse, getTimestamp(startDate), getTimestamp(endDate), type)
                .stream()
                .map(media -> MediaDto.builder()
                        .type(media.getType())
                        .name(media.getName())
                        .author(media.getAuthor())
                        .themes(media.getThemes())
                        .publishingHouse(media.getPublishingHouse())
                        .printDate(media.getPrintDate().getTime())
                        .identifier(media.getIdentifier())
                        .build())
                .collect(Collectors.toList());
    }

    private Timestamp getTimestamp(Long time) {

        return time == null ? null : new Timestamp(time);
    }

    @Override
    public void delete(String identifier) {
        mediaRepository.deleteByIdentifier(identifier);
    }

    @Override
    public void update(MediaDto mediaDto) {

        Media media = mediaRepository.findByIdentifier(mediaDto.getIdentifier());
        if (media != null) {
            media.setType(mediaDto.getType());
            media.setName(mediaDto.getName());
            media.setAuthor(mediaDto.getAuthor());
            media.setThemes(mediaDto.getThemes());
            media.setPublishingHouse(mediaDto.getPublishingHouse());
            media.setPrintDate(new Timestamp(mediaDto.getPrintDate()));
            mediaRepository.save(media);
        }
    }

    @Override
    public MediaDto getMedia(String identifier) {

        Media media = mediaRepository.findByIdentifier(identifier);
        if (media != null) {
            return MediaDto.builder()
                    .type(media.getType())
                    .name(media.getName())
                    .author(media.getAuthor())
                    .themes(media.getThemes())
                    .publishingHouse(media.getPublishingHouse())
                    .printDate(media.getPrintDate().getTime())
                    .identifier(media.getIdentifier())
                    .build();
        } else {
            return null;
        }
    }
}

package com.intervale.crud.api;

import com.intervale.crud.api.dto.GetAllResponse;
import com.intervale.crud.api.dto.MediaRequestResponse;
import com.intervale.crud.repository.entity.Media;
import com.intervale.crud.service.MediaService;
import com.intervale.crud.service.dto.MediaDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/media")
public class ApiController {

    private MediaService mediaService;

    @PutMapping("/add")
    public ResponseEntity<Media> addMedia(@RequestBody MediaRequestResponse request) {

        if (request == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            this.mediaService.save(MediaDto.builder()
                    .type(request.getType())
                    .name(request.getName())
                    .author(request.getAuthor())
                    .themes(request.getThemes())
                    .publishingHouse(request.getPublishingHouse())
                    .printDate(request.getPrintDate())
                    .build());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("{identifier}")
    public ResponseEntity<MediaDto> getByIdentifier(@PathVariable String identifier) {

        MediaDto media = this.mediaService.getMedia(identifier);
        if (media == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(media, HttpStatus.OK);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<GetAllResponse> getAllMedia(@RequestParam(required = false) String author,
                                                      @RequestParam(required = false) String themes,
                                                      @RequestParam(required = false) String publishingHouse,
                                                      @RequestParam(required = false) Long startDate,
                                                      @RequestParam(required = false) Long endDate,
                                                      @RequestParam(required = false) String type) {

        List<MediaDto> media = this.mediaService.getAll(author, themes, publishingHouse, startDate, endDate, type);
        if (media.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(new GetAllResponse(media.stream().map(mediaDto -> MediaRequestResponse.builder()
                            .type(mediaDto.getType())
                            .name(mediaDto.getName())
                            .author(mediaDto.getAuthor())
                            .themes(mediaDto.getThemes())
                            .publishingHouse(mediaDto.getPublishingHouse())
                            .printDate(mediaDto.getPrintDate())
                            .identifier(mediaDto.getIdentifier())
                            .build())
                    .collect(Collectors.toList())), HttpStatus.OK);
        }
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody MediaRequestResponse request) {
        if (request != null) {
            mediaService.update(MediaDto.builder()
                    .type(request.getType())
                    .name(request.getName())
                    .author(request.getAuthor())
                    .themes(request.getThemes())
                    .publishingHouse(request.getPublishingHouse())
                    .printDate(request.getPrintDate())
                    .identifier(request.getIdentifier())
                    .build());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{identifier}")
    public ResponseEntity<MediaDto> delete(@PathVariable String identifier) {
        if (identifier != null) {
            mediaService.delete(identifier);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

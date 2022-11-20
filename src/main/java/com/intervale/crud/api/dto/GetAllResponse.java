package com.intervale.crud.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetAllResponse {

    List<MediaRequestResponse> mediaList;

    public GetAllResponse(List<MediaRequestResponse> mediaList) {
        this.mediaList = mediaList;
    }
}


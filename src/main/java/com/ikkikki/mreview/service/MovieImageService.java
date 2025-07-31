package com.ikkikki.mreview.service;

import com.ikkikki.mreview.domain.dto.MovieDTO;
import com.ikkikki.mreview.domain.dto.MovieImageDTO;
import com.ikkikki.mreview.domain.entity.Movie;
import com.ikkikki.mreview.domain.entity.MovieImage;

public interface MovieImageService {
  static MovieImage toEntity(MovieImageDTO dto) {
    return MovieImage.builder()
            .movie(Movie.builder().mno(dto.getMno()).build())
            .uuid(dto.getUuid())
            .path(dto.getPath())
            .imgName(dto.getOrigin())
            .build();
  }

}

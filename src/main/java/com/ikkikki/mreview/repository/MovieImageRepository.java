package com.ikkikki.mreview.repository;

import com.ikkikki.mreview.domain.entity.Movie;
import com.ikkikki.mreview.domain.entity.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}

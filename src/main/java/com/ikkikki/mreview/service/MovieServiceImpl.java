package com.ikkikki.mreview.service;

import com.ikkikki.mreview.domain.dto.MovieDTO;
import com.ikkikki.mreview.domain.dto.PageRequestDTO;
import com.ikkikki.mreview.domain.dto.PageResponseDTO;
import com.ikkikki.mreview.domain.entity.Movie;
import com.ikkikki.mreview.domain.entity.MovieImage;
import com.ikkikki.mreview.repository.MovieImageRepository;
import com.ikkikki.mreview.repository.MovieRepository;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
@Data
public non-sealed class MovieServiceImpl implements MovieService {
  private final MovieRepository movieRepository;
  private final MovieImageRepository movieImageRepository;

  @Override
  @Transactional
  public Long register(MovieDTO dto) {
    // 리뷰 등록
    Map<String, Object> map = toEntity(dto);
    Movie movie = (Movie) map.get("movie");
    movieRepository.save(movie);

    List<MovieImage> list = ((List<MovieImage>) map.get("images"));
//    list.forEach(image -> image.setMovie(movie));
    list.forEach(movieImageRepository::save);

    return movie.getMno();
  }

  @Override
  public PageResponseDTO<MovieDTO, Object[]> getList(PageRequestDTO dto) {
    return new PageResponseDTO<>(movieRepository.getListPage(dto.getPageable(Sort.by(Sort.Direction.DESC, "mno"))),
                arr -> toDTO((Movie) arr[0],
                        (List<MovieImage>) (Arrays.asList((MovieImage) arr[1])),
                        (Double) arr[2],
                        (Long)arr[3]));
  }

  @Override
  public MovieDTO get(Long mno) {
    List<Object[]> list = movieRepository.getMovieWithAll(mno);
//    list.forEach(m -> log.info(Arrays.toString(m)));
    Movie movie = (Movie) list.getFirst()[0] ;
    List<MovieImage> movieImages = (Arrays.asList((MovieImage) list.getFirst()[1]));
    Double avg =(Double) list.getFirst()[2];
    Long reviewCnt = (Long) list.getFirst()[3];
    return toDTO(movie, movieImages, avg, reviewCnt);
  }


}

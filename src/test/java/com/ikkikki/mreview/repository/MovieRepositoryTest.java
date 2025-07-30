package com.ikkikki.mreview.repository;

import com.ikkikki.mreview.domain.entity.Movie;
import com.ikkikki.mreview.domain.entity.MovieImage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

@Slf4j
@SpringBootTest
public class MovieRepositoryTest {
  @Autowired
  private MovieRepository movieRepository;
  @Autowired
  private MovieImageRepository movieImageRepository;

  @Test
  public void testExist() {
    Assertions.assertNotNull(movieImageRepository);
  }

  @Commit
  @Transactional
  @Test
  public void insertMovies() {
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Movie movie = Movie.builder().title("Movie..." + i ).build();
      System.out.println("--------------------------");
      movieRepository.save(movie);

      int count = (int)(Math.random() * 5) + 1;

      for(int j = 0; j < count; j++) {
        MovieImage movieImage = MovieImage.builder()
                .uuid(UUID.randomUUID().toString())
                .movie(movie)
                .imgName("test" + j + ".jpg")
                .build();
        movieImageRepository.save(movieImage);
      }
      System.out.println("------------------------");
    });
  }

  @Test
  public void testGetListPage() {
//    PageRequest  pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
//    Page<Object[]> result = movieRepository.getListPage(pageRequest);
//    for (Object[] objects : result.getContent()) {
//      System.out.println(Arrays.toString(objects));
//    }
    movieRepository.getListPage(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"))).forEach(m -> log.info(Arrays.toString(m)));
  }

  @Test
  public void testGetMovieWithAll() {
    movieRepository.getMovieWithAll(200L).forEach(m -> log.info(Arrays.toString(m)));
  }

}

package com.ikkikki.mreview.repository;

import com.ikkikki.mreview.domain.entity.Member;
import com.ikkikki.mreview.domain.entity.Movie;
import com.ikkikki.mreview.domain.entity.Review;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class ReviewRepositoryTest {
  @Autowired
  private ReviewRepository reviewRepository;


  @Test
  public void testExist() {
    Assertions.assertNotNull(reviewRepository);
  }

  @Test
  @Transactional
  public void insertReviews() {
    IntStream.rangeClosed(1, 200).forEach(i -> {
      Long mno = new Random().nextLong(200) + 1;
      Long mid = new Random().nextLong(99) + 1; //회원이 99명이라서
      Member member = Member.builder().mid(mid).build();
      Movie movie = Movie.builder().mno(mno).build();

      reviewRepository.save(Review.builder()
              .member(member)
              .movie(movie)
              .grade(new Random().nextInt(5) + 1)
              .text("이 영화에 대한 느낌" + i)
              .build());
    });
  }

  @Test
//  @Transactional(readOnly = true)
  public void testFindByMovieMno() {
    reviewRepository.findByMovie_mno(100L).forEach(r -> {
      log.info(r);
      log.info(r.getMember().getEmail());
//      log.info(r.getMovie().getTitle());
    });



  }

}
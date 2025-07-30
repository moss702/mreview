package com.ikkikki.mreview.repository;

import com.ikkikki.mreview.domain.entity.Movie;
import com.ikkikki.mreview.domain.entity.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
  List<Review> findByMovie_mno(Long mno);

  @Modifying
  @Query("delete from Review r where r.member.mid = ?1")
  void deleteByMember_mid(Long mid);

}

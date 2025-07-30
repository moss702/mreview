package com.ikkikki.mreview.repository;

import com.ikkikki.mreview.domain.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTest {
  @Autowired
  private MemberRepository memberRepository;
  @Autowired
  private ReviewRepository reviewRepository;

  @Test
  public void testExist() {
    Assertions.assertNotNull(memberRepository);
  }

  @Test
  public void insertMembers() {
    IntStream.range(1, 100).forEach(i -> {
      Member member = Member.builder()
              .email("r" + i + "@zerock.org")
              .pw("1111")
              .nickname("reviewer" + i)
              .build();
      memberRepository.save(member);
    });
  }

  @Test
  @Transactional
  @Commit
  public void testDeleteByMemberMid() {
    Long mid = 5L;
//    reviewRepository.deleteByMember_mid(mid);
    memberRepository.deleteById(mid);

  }
  
}

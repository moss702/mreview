package com.ikkikki.mreview.repository;

import com.ikkikki.mreview.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

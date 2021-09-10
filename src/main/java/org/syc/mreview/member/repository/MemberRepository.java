package org.syc.mreview.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syc.mreview.member.entity.Member;

// 신규 작성(363p)
public interface MemberRepository extends JpaRepository<Member, Long> {
}

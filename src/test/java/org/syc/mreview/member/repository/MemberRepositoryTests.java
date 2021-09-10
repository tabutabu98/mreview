package org.syc.mreview.member.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.syc.mreview.member.entity.Member;
import org.syc.mreview.review.repository.ReviewRepository;

import java.util.stream.IntStream;

// 신규 작성(363p)
@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    // 신규 작성(383p), 회원을 이용한 삭제 메서드를 받아오기 위한 추가
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers() {

        // 100명의 더미 맴버 생성
        IntStream.rangeClosed(1, 100).forEach(i -> {

            Member member = Member.builder()
                    .email("r" + i + "@partdb.com")
                    .pw("a")
                    .nickname("reviewer" + i)
                    .build();

            memberRepository.save(member);

        });

    }

    // 신규 작성(383p), 회원을 이용한 삭제 메서드를 받아오기 위한 추가
    // 추가 수정(384p), 1. FK를 가지는 Review쪽을 먼저 삭제하지 않고, 2. 트랜젝션 관련 처리가 없음. 그 오류를 해결하기위해서 @Transactional, @Commit 추가
    @Test
    @Transactional
    @Commit
    public void testDeleteMember() {

        Long mid = 1L;  // Member의 mid, 1번 회원은 강제퇴장ㅋㅋ

        Member member = Member.builder().mid(mid).build();

        // 기존
//        memberRepository.deleteById(mid);
//        reviewRepository.deleteByMember(member);

        // 신규 작성(384p), 순서 주의
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);

    }

}

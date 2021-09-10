package org.syc.mreview.member.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.syc.mreview.member.entity.Member;

import java.util.stream.IntStream;

// 신규 작성(363p)
@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

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

}

package org.syc.mreview.review.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.syc.mreview.member.entity.Member;
import org.syc.mreview.movie.entity.Movie;
import org.syc.mreview.review.entity.Review;

import java.util.List;

// 신규 작성(365p)
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 신규 작성(379p), 특정 영화 번호를 이용해서 해당 영화를 리뷰한 정보
    // 추가 작성(381p), @Entity 추가
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    // 신규 작성(382p), 회원을 이용해서 삭제하는 메서드
    void deleteByMember(Member member);

}

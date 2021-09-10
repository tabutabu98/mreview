package org.syc.mreview.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syc.mreview.movie.entity.Movie;
import org.syc.mreview.review.entity.Review;

import java.util.List;

// 신규 작성(365p)
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 신규 작성(379p), 특정 영화 번호를 이용해서 해당 영화를 리뷰한 정보
    List<Review> findByMovie(Movie movie);

}

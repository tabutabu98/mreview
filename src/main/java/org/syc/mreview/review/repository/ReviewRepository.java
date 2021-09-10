package org.syc.mreview.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syc.mreview.review.entity.Review;

// 신규 작성(365p)
public interface ReviewRepository extends JpaRepository<Review, Long> {
}

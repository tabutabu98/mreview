package org.syc.mreview.review.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.syc.mreview.member.entity.Member;
import org.syc.mreview.movie.entity.Movie;
import org.syc.mreview.review.entity.Review;

import java.util.stream.IntStream;

// 신규 작성(366p)
@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    // 더미 영화 리뷰 기입
    @Test
    public void insertMovieReviews() {

        // 200개의 리뷰를 등록
        IntStream.rangeClosed(1, 200).forEach(i -> {

            // 영화 번호
            Long mno = (long)(Math.random() * 100) + 1;

            // 리뷰어 번호
            Long mid = ((long)(Math.random() * 100) + 1 );

            Member member = Member.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random() * 5) + 1)
                    .text("Latte is Horse...." + i)
                    .build();

            reviewRepository.save(movieReview);
        });

    }

}
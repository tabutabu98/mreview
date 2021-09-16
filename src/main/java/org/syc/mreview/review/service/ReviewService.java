package org.syc.mreview.review.service;

import org.syc.mreview.member.entity.Member;
import org.syc.mreview.movie.entity.Movie;
import org.syc.mreview.review.dto.ReviewDTO;
import org.syc.mreview.review.entity.Review;

import java.util.List;

// 신규 작성(451p)
public interface ReviewService {

    // 영화의 모든 영화리뷰를 가져온다.
    List<ReviewDTO> getListOfMovie(Long mno);

    // 영화 리뷰를 추가
    Long register(ReviewDTO movieReviewDTO);

    // 특정한 영화리뷰 수정
    void modify(ReviewDTO movieReviewDTO);

    // 특정한 영화 리뷰 삭제
    void remove(Long reviewnum);

    default Review dtoToEntity(ReviewDTO movieReviewDTO){

        Review movieReview = Review.builder()
                .reviewnum(movieReviewDTO.getReviewnum())
                .movie(Movie.builder().mno(movieReviewDTO.getMno()).build())
                .member(Member.builder().mid(movieReviewDTO.getMid()).build())
                .grade(movieReviewDTO.getGrade())
                .text(movieReviewDTO.getText())
                .build();

        return movieReview;
    }

    default ReviewDTO entityToDTO(Review movieReview){

        ReviewDTO movieReviewDTO = ReviewDTO.builder()
                .reviewnum(movieReview.getReviewnum())
                .mno(movieReview.getMovie().getMno())
                .mid(movieReview.getMember().getMid())
                .nickname(movieReview.getMember().getNickname())
                .email(movieReview.getMember().getEmail())
                .grade(movieReview.getGrade())
                .text(movieReview.getText())
                .regDate(movieReview.getRegDate())
                .modDate(movieReview.getModDate())
                .build();

        return movieReviewDTO;
    }

}

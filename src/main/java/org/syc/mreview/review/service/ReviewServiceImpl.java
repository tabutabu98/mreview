package org.syc.mreview.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.syc.mreview.movie.entity.Movie;
import org.syc.mreview.review.dto.ReviewDTO;
import org.syc.mreview.review.entity.Review;
import org.syc.mreview.review.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// 신규 작성(452p)
@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    // 리스트 로직
    @Override
    public List<ReviewDTO> getListOfMovie(Long mno){

        Movie movie = Movie.builder().mno(mno).build();

        List<Review> result = reviewRepository.findByMovie(movie);

        return result.stream().map(movieReview -> entityToDTO(movieReview)).collect(Collectors.toList());
    }

    // 등록 로직
    @Override
    public Long register(ReviewDTO movieReviewDTO){

        Review movieReview = dtoToEntity(movieReviewDTO);

        reviewRepository.save(movieReview);

        return movieReview.getReviewnum();
    }

    // 수정 로직
    @Override
    public void modify(ReviewDTO movieReviewDTO){

        Optional<Review> result = reviewRepository.findById(movieReviewDTO.getReviewnum());

        if (result.isPresent()){

            Review movieReview = result.get();
            movieReview.changeGrade(movieReviewDTO.getGrade());
            movieReview.changeText(movieReviewDTO.getText());

            reviewRepository.save(movieReview);
        }
    }

    // 삭제 로직
    @Override
    public void remove(Long reviewnum){

        reviewRepository.deleteById(reviewnum);

    }
}

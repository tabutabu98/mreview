package org.syc.mreview.movie.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.syc.mreview.common.dto.PageRequestDTO;
import org.syc.mreview.common.dto.PageResultDTO;
import org.syc.mreview.movie.dto.MovieDTO;
import org.syc.mreview.movie.entity.Movie;
import org.syc.mreview.movie.entity.MovieImage;
import org.syc.mreview.movie.repository.MovieImageRepository;
import org.syc.mreview.movie.repository.MovieRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

// 신규 작성(420p)
@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    // final
    private final MovieRepository movieRepository;

    // 추가 수정(422p), final
    private final MovieImageRepository imageRepository;

    // 추가 수정(423p), @Transactional 등등 추가
    @Transactional
    @Override
    public Long register(MovieDTO movieDTO){

        Map<String, Object> entityMap = dtoToEntity(movieDTO);
        Movie movie = (Movie) entityMap.get("movie");
        List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

        movieRepository.save(movie);

        movieImageList.forEach(movieImage -> {
            imageRepository.save(movieImage);
        });

        return movie.getMno();
    }

    // 신규 작성(437p)
    @Override
    public PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getListPage(pageable);

        Function<Object[], MovieDTO> fn = (arr -> entitiesToDTO(
                (Movie)arr[0],
                (List<MovieImage>) (Arrays.asList((MovieImage)arr[1])),
                (Double) arr[2],
                (Long) arr[3])
        );

        return new PageResultDTO<>(result, fn);
    }

    // 신규 작성(444p)
    @Override
    public MovieDTO getMovie(Long mno) {

        List<Object[]> result = movieRepository.getMovieWithAll(mno);

        // Movie 엔티티는 가장 앞에 존재 - 모든 Row가 동일한 값
        Movie movie = (Movie) result.get(0)[0];

        // 영화의 이미지 개수만큼 MovieImage객체 필요
        List<MovieImage> movieImageList = new ArrayList<>();

        result.forEach(arr -> {
            MovieImage movieImage = (MovieImage) arr[1];
            movieImageList.add(movieImage);
        });

        // 평균 평점 - 모든 Row가 동일한 값
        Double avg = (Double) result.get(0)[2];
        // 리뷰 개수 - 모든 Row가 동일한 값
        Long reviewCnt = (Long) result.get(0)[3];

        return entitiesToDTO(movie, movieImageList, avg, reviewCnt);
    }
}

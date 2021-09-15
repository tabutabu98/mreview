package org.syc.mreview.movie.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.syc.mreview.movie.dto.MovieDTO;
import org.syc.mreview.movie.entity.Movie;
import org.syc.mreview.movie.entity.MovieImage;
import org.syc.mreview.movie.repository.MovieImageRepository;
import org.syc.mreview.movie.repository.MovieRepository;

import java.util.List;
import java.util.Map;

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

}

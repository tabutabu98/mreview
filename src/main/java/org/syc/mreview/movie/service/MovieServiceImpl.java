package org.syc.mreview.movie.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.syc.mreview.movie.dto.MovieDTO;
import org.syc.mreview.movie.repository.MovieRepository;

// 신규 작성(420p)
@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    @Override
    public Long register(MovieDTO movieDTO){
        return null;
    }

}

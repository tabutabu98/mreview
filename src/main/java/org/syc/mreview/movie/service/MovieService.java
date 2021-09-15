package org.syc.mreview.movie.service;

import org.syc.mreview.movie.dto.MovieDTO;
import org.syc.mreview.movie.dto.MovieImageDTO;
import org.syc.mreview.movie.entity.Movie;
import org.syc.mreview.movie.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 신규 작성(420p)
// 추가 수정(421p)
public interface MovieService {

    Long register(MovieDTO movieDTO);

    // Map타입으로 반환
    default Map<String, Object> dtoToEntity(MovieDTO movieDTO) {

        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();

        entityMap.put("movie", movie);

        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();
        // MovieImage DTO처리
        if (imageDTOList != null && imageDTOList.size() > 0) {
            List<MovieImage> movieImageList = imageDTOList.stream()
                    .map(movieImageDTO -> {
                        MovieImage movieImage = MovieImage.builder()
                                .path(movieImageDTO.getPath())
                                .imgName(movieImageDTO.getImgName())
                                .uuid(movieImageDTO.getUuid())
                                .movie(movie)
                                .build();

                        return movieImage;
                    }).collect(Collectors.toList());
            entityMap.put("imgList", movieImageList);
        }
        return entityMap;
    }

}

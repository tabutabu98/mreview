package org.syc.mreview.movie.service;

import org.syc.mreview.common.dto.PageRequestDTO;
import org.syc.mreview.common.dto.PageResultDTO;
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

    // 신규 작성(436p)
    Long register(MovieDTO movieDTO);

    // 목록 처리를 위한 getList
    PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO);

    // 신규 작성(443p)
    MovieDTO getMovie(Long mno);

    default MovieDTO entitiesToDTO(Movie movie, List<MovieImage> movieImages, Double avg, Long reviewCnt) {
        // Movie
        MovieDTO movieDTO = MovieDTO.builder()
                .mno(movie.getMno())
                .title(movie.getTitle())
                .regDate(movie.getRegDate())
                .modDate(movie.getModDate())
                .build();

        // MovieImage
        List<MovieImageDTO> movieImageDTOList = movieImages.stream().map(movieImage -> {
            return MovieImageDTO.builder()
                    .imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .uuid(movieImage.getUuid())
                    .build();
        }).collect(Collectors.toList());

        movieDTO.setImageDTOList(movieImageDTOList);
        movieDTO.setAvg(avg);
        movieDTO.setReviewCnt(reviewCnt.intValue());

        return movieDTO;
    }
    // 신규 작성 끝(437p)

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

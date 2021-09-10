package org.syc.mreview.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syc.mreview.movie.entity.MovieImage;

// 신규 작성(360p)
public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}

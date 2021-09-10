package org.syc.mreview.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.syc.mreview.movie.entity.Movie;

// 신규 작성(360p)
public interface MovieRepository extends JpaRepository<Movie, Long> {
}

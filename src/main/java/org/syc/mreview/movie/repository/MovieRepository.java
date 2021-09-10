package org.syc.mreview.movie.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.syc.mreview.movie.entity.Movie;

import java.util.List;

// 신규 작성(360p)
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // JPQL(369p), 띄어쓰기 중요....
    // JPQL에 MovieImage도 결합(370p)
//    @Query("select m, max(mi), avg(coalesce(r.grade,0)), count(distinct r) from Movie m " +
//            "left outer join MovieImage mi on mi.movie = m  " +
//            "left outer join Review r on r.movie = m group by m")
//    Page<Object[]> getListPage(Pageable pageable);

    // 페이징 처리 JPQL 수정(372p), max() 처리 제거로 인해 반복적인 실행 없이, 목록을 구하는 쿼리와 개수를 구하는 쿼리만 실행(항상 페이징 처리가 되는 부분에서는 limit 등이 정상적으로 실행되는지 확인)
    @Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) from Movie m " +
            "left outer join MovieImage mi on mi.movie = m  " +
            "left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);

    // 신규 작성(375p), 특정 영화 조회 처리
//    @Query("select m, mi " +
//            "from Movie m left outer join MovieImage mi on mi.movie = m " +
//            "where m.mno = :mno")
//    List<Object[]> getMovieWithAll(long mno);

    // getMovieWithAll() 수정(377p)
    @Query("select m, mi, avg(coalesce(r.grade,0)), count(r) " +
            "from Movie m left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m " +
            "where m.mno = :mno group by mi")
    List<Object[]> getMovieWithAll(long mno);
}
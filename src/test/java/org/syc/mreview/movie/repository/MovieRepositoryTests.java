package org.syc.mreview.movie.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.syc.mreview.movie.entity.Movie;
import org.syc.mreview.movie.entity.MovieImage;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

// 신규 작성(361p)
@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository imageRepository;

    @Commit
    @Transactional
    @Test
    public void insertMovies() {
        IntStream.rangeClosed(1, 100).forEach(i ->{

            // movie의 더미 데이터 만들기(제목)
            Movie movie = Movie.builder().title("Movie..." + i).build();

            System.out.println("----------------------------------------------------");

            movieRepository.save(movie);

            int count = (int)(Math.random() * 5) + 1;   // 1, 2, 3, 4

            for (int j = 0; j < count; j++) {

                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg")
                        .build();

                imageRepository.save(movieImage);
            }

            System.out.println("=====================================================");

        });
    }   // 테스트의 끝

    // Movie와 Review를 이용해서 페이징 처리하는 테스트
    @Test
    public void testListPage() {

        PageRequest pageRequest = PageRequest.of(0,10, Sort.by(Sort.Direction.DESC, "mno"));

        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        for(Object[] objects : result.getContent()) {

            System.out.println(Arrays.toString(objects));

        }

    }   // test의 끝

}

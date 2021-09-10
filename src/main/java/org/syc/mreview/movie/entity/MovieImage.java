package org.syc.mreview.movie.entity;

import lombok.*;

import javax.persistence.*;

//신규 작성(353p)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "movie")    // 연관 관계 주의
public class MovieImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;  // 이미지 번호

    private String uuid;    // 고유 번호

    private String imgName; // 이미지 이름

    private String path;    // 이미지 저장 경로 '년/월/일'

    @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩
    private Movie movie;    // Movie Entity의 movie Column

}

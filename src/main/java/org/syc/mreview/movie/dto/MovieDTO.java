package org.syc.mreview.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 신규 작성(418p)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    // 영화 번호
    private Long mno;

    // 영화 제목
    private String title;

    @Builder.Default
    private List<MovieImageDTO> imageDTOList = new ArrayList<>();

    // 신규 작성(436p)
    // 영화의 평균 평점
    private double avg;

    // 리뷰 수 jpa의 count()
    private int reviewCnt;

    // 생성일
    private LocalDateTime regDate;

    // 수정일
    private LocalDateTime modDate;

}

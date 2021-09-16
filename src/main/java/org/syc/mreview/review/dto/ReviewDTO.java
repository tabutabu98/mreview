package org.syc.mreview.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 신규 작성(449p)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    // 리뷰 번호
    private Long reviewnum;

    // 영화 번호
    private  Long mno;

    // 회원 id
    private Long mid;

    // 회원 닉네임
    private String nickname;

    // 회원 이메일
    private String email;

    // 리뷰 등급
    private int grade;

    // 리뷰 내용
    private String text;

    // 생성일
    private LocalDateTime regDate;

    // 수정일
    private LocalDateTime modDate;

}

/*
* ReviewDTO는 화면에 필요한 모든 내용을 가지고 있어햐 한다.
*
* ReviewService는 기존의 서비스가 하는 작업과 거의 동일
*
* entityToDTO(), dtoToEntity() 메서드를 정의해야함
* 이 메서드에 추가할 기능들은
* - 특정한 영화의 모든 리뷰를 가져오는 기능
* - 새로운 영화 리뷰를 등록하는 기능
* - 특정 영화 리뷰를 수정하는 기능
* - 특정 영화 리뷰를 삭제하는 기능
*
* 리뷰를 수정하는 기능을 넣기 위해서는 Review Entity를 수정할 필요가 있음*/
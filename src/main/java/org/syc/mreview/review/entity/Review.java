package org.syc.mreview.review.entity;

import lombok.*;
import org.syc.mreview.common.entity.BaseEntity;
import org.syc.mreview.member.entity.Member;
import org.syc.mreview.movie.entity.Movie;

import javax.persistence.*;

// 신규 작성(358p)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"movie", "member"})    // Movie Entity의 PK인 'movie' Column과 Member Entity의 PK인 'member' Column을 참조
public class Review extends BaseEntity {

    @Id
    @GeneratedValue
    private Long reviewnum; // 리뷰 번호

    @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩
    private Movie movie;    // Movie Entity의 movie Column

    @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩
    private Member member;  // Member Entity의 member Column

    private int grade;  // 리뷰 등급

    private String text;    // 리뷰 내용


    // 신규 작성(450p), 리뷰 평점과 리뷰 내용을 수정할 수 있도록 처리하기 위해 추가
    // 이 두 메서드의 기능을 서비스하기 위해서는 ReviewService와 ReviewServiceImpl에 코드를 추가해줘야함
    // changeGrade() 메서드 추가
    public void changeGrade(int grade){
        this.grade = grade;
    }

    // changeText() 메서드
    public void changeText(String text){
        this.text = text;
    }
}

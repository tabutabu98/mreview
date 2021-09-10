package org.syc.mreview.member.entity;

import lombok.*;
import org.syc.mreview.common.entity.BaseEntity;

import javax.persistence.*;

// 신규 작성(355p)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name = "m_member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;       // Member의 id

    private String email;   // Member의 이메일

    private String pw;      // Member의 비밀번호

    private String nickname;    // Member의 닉네임임

}
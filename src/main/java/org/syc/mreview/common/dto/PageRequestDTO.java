package org.syc.mreview.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

// 신규 작성(434p), board 프로젝트에서 가져옴
@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {   // 컨트롤러에서 서비스로 전달   // board(게시판) 프로젝트를 위해 신규 작성(263p)

    private int page;
    private int size;

    // 203p 추가 : 검색을 위해서
    private String type;
    private String keyword;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort) {

        return PageRequest.of(page -1, size, sort);
    }
}

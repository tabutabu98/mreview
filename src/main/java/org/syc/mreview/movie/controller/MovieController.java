package org.syc.mreview.movie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.syc.mreview.movie.dto.MovieDTO;
import org.syc.mreview.movie.service.MovieService;

// 신규 작성(415p)
// 추가 수정(424p)
@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    // 신규 작성(424p), final
    private final MovieService movieService;

    // 영화 등록 화면 겟맵핑, register
    @GetMapping("/register")
    public void register() {

    }

    // 신규 작성(424p), 영화 등록 화면의 포스트맵핑, register
    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes){

        log.info("movieDTO: " + movieDTO);

        Long mno = movieService.register(movieDTO);

        redirectAttributes.addFlashAttribute("msg", mno);

        return "redirect:/movie/list";
    }

}
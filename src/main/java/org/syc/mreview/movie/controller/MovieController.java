package org.syc.mreview.movie.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.syc.mreview.common.dto.PageRequestDTO;
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

    // 신규 작성(438p)
    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        log.info("pageRequestDTO: " + pageRequestDTO);

        model.addAttribute("result", movieService.getList(pageRequestDTO));

    }

    // 신규 작성(445p), 리드페이지, 수정페이지의 겟맵핑
    @GetMapping({"/read", "/modify"})
    public void read(long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {

        log.info("mno: " + mno);

        MovieDTO movieDTO = movieService.getMovie(mno);

        model.addAttribute("dto", movieDTO);

    }
}
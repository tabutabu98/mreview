package org.syc.mreview.movie;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// 신규 작성(415p)
@Controller
@RequestMapping("/movie")
@Log4j2
public class MovieController {

    @GetMapping("/register")
    public void register() {

    }

}
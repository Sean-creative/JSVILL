package com.sjs.jsvill.controller.letter;

import com.sjs.jsvill.dto.letter.LetterDTO;
import com.sjs.jsvill.service.letter.LetterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiLetterWp {

    private final LetterService letterService;

    @RequestMapping("/letter/wp")
    @ResponseBody
    public Long appLetter(@RequestBody LetterDTO dto) {
        log.info("user letter");
        Long letterR = letterService.register(dto);
        return letterR;
    }


}

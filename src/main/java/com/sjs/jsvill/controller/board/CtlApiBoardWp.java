package com.sjs.jsvill.controller.board;

import com.sjs.jsvill.dto.board.BoardDTO;
import com.sjs.jsvill.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiBoardWp {

    private final BoardService boardService;

    @RequestMapping("/board/wp")
    @ResponseBody
    public Long appBoardWp(@RequestBody BoardDTO dto) {
        log.info("user board wp");
        Long boardR = boardService.register(dto);
        return boardR;
    }


}

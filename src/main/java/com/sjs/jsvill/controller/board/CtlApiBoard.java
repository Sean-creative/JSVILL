package com.sjs.jsvill.controller.board;

import com.sjs.jsvill.dto.board.BoardResDTO;
import com.sjs.jsvill.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiBoard {

    private final BoardService boardService;

    @RequestMapping("/board")
    @ResponseBody
    public List<BoardResDTO> appBoard() {
        log.info("user board");
        List<BoardResDTO> result = boardService.getBoardList();
        return result;
    }
}

package com.sjs.jsvill.controller.board;

import com.sjs.jsvill.dto.board.BoardDTO;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.service.board.BoardService;
import com.sjs.jsvill.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@Log4j2
@RequiredArgsConstructor
public class CtlApiBoardWp {

    private final BoardService boardService;
    private final MemberService memberService;

    @RequestMapping("/board/wp/{phone}")
    @ResponseBody
    public Long appBoardWp(@PathVariable String phone, @RequestBody BoardDTO dto) {
        log.info("user board wp");
        Long result = 0L;
        Optional<Member> user = memberService.findByPhoneNumber(phone);
        if(user.isPresent()) {
            dto.setMemberR(user.get().getMemberRowid());
            result = boardService.register(dto);
        }
        return result;
    }


}

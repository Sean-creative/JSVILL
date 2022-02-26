package com.sjs.jsvill.service.letter;

import com.sjs.jsvill.dto.board.BoardDTO;
import com.sjs.jsvill.dto.board.BoardResDTO;
import com.sjs.jsvill.dto.letter.LetterDTO;
import com.sjs.jsvill.entity.Letter;

import java.util.List;

public interface LetterService {
    Long register(LetterDTO dto);
    default Letter dtoToEntity(LetterDTO dto) {
        Letter letter = Letter.builder()
                .contents(dto.getContents())
                .checked(0)
                .build();
        return letter;
    }
}

package com.sjs.jsvill.service.jimmy.letter;

import com.sjs.jsvill.dto.jimmy.letter.LetterDTO;
import com.sjs.jsvill.entity.jimmy.Letter;

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

package com.sjs.jsvill.service.letter;

import com.sjs.jsvill.dto.letter.LetterDTO;
import com.sjs.jsvill.entity.Letter;
import com.sjs.jsvill.repository.LetterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


@Service
@Log4j2
@RequiredArgsConstructor // Repository 주입 해줌
public class LetterServiceImpl implements LetterService {

    private final LetterRepository letterRepository;

    @Override
    public Long register(LetterDTO dto) {
        Letter letter = dtoToEntity(dto);
        Letter returnLetter = letterRepository.save(letter);
        return returnLetter.getLetter_rowid();
    }
}

package com.sjs.jsvill.service.jimmy.board;

import com.sjs.jsvill.dto.jimmy.board.BoardDTO;
import com.sjs.jsvill.dto.jimmy.board.BoardResDTO;
import com.sjs.jsvill.repository.jimmy.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO dto) {
        return boardRepository.save(dtoToEntity(dto)).getBoard_rowid();
    }

    @Override
    public List<BoardResDTO> getBoardList() {
        return dtoListToEntity(boardRepository.findAll());
    }

}

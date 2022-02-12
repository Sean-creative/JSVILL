package com.sjs.jsvill.service.board;

import com.sjs.jsvill.dto.board.BoardDTO;
import com.sjs.jsvill.entity.Board;
import com.sjs.jsvill.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO dto) {
        Board board = dtoToEntity(dto);
        Board returnBoard = boardRepository.save(board);
        return returnBoard.getBoard_rowid();
    }
}

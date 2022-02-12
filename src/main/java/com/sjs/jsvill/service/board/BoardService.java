package com.sjs.jsvill.service.board;


import com.sjs.jsvill.dto.board.BoardDTO;
import com.sjs.jsvill.entity.Board;
import com.sjs.jsvill.entity.Member;

public interface BoardService {
    Long register(BoardDTO dto);
    default Board dtoToEntity(BoardDTO dto){
        Member member = Member.builder().member_rowid(dto.getMemberR()).build();
        Board board = Board.builder()
                .contents(dto.getContents())
                .member_rowid(member)
                .build();
        return board;
    }
}


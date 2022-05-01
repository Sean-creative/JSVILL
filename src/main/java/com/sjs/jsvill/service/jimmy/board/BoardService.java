package com.sjs.jsvill.service.jimmy.board;


import com.sjs.jsvill.dto.jimmy.board.BoardDTO;
import com.sjs.jsvill.dto.jimmy.board.BoardResDTO;
import com.sjs.jsvill.entity.jimmy.Board;
import com.sjs.jsvill.entity.sean.Member;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface BoardService {
    Long register(BoardDTO dto);
    List<BoardResDTO> getBoardList();
    default Board dtoToEntity(BoardDTO dto){
        Member member = Member.builder().member_rowid(dto.getMemberR()).build();
        Board board = Board.builder()
                .contents(dto.getContents())
                .member_rowid(member)
                .build();
        return board;
    }

    default List<BoardResDTO> dtoListToEntity(List<Board> list) {
        List<BoardResDTO> returnList = new ArrayList<>();
        for(int i=0; i<list.size(); i++) {
            Board eBoard = list.get(i);
            BoardResDTO dBoard = BoardResDTO.builder()
                    .contents(eBoard.getContents())
                    .regDate(eBoard.getRegDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .writer(eBoard.getMember_rowid().getName())
                    .isDeleted("1".equals(eBoard.getIsdeleted()))
                    .isAdmin(10==eBoard.getMember_rowid().get_memberType().get_membertype_rowid())
                    .build();
            returnList.add(dBoard);
        }
        return returnList;
    }
}


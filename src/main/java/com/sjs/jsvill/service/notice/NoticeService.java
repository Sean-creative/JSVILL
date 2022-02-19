package com.sjs.jsvill.service.notice;


import com.sjs.jsvill.dto.board.BoardDTO;
import com.sjs.jsvill.dto.board.BoardResDTO;
import com.sjs.jsvill.dto.notice.NoticeDTO;
import com.sjs.jsvill.dto.notice.NoticeResDTO;
import com.sjs.jsvill.entity.Board;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity.Notice;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public interface NoticeService {
    Long register(NoticeDTO dto);
    List<NoticeResDTO> getNoticeList();
    default Notice dtoToEntity(NoticeDTO dto){
        Member member = Member.builder().member_rowid(dto.getMemberR()).build();
        Notice notice = Notice.builder()
                .title(dto.getTitle())
                .contents(dto.getContents())
                .member_rowid(member)
                .build();
        return notice;
    }

    default List<NoticeResDTO> dtoListToEntity(List<Notice> list) {
        List<NoticeResDTO> returnList = new ArrayList<>();
        for(int i=0; i<list.size(); i++) {
            Notice eNotice = list.get(i);
            NoticeResDTO dNotice = NoticeResDTO.builder()
                    .title(eNotice.getTitle())
                    .contents(eNotice.getContents())
                    .writer(eNotice.getMember_rowid().getName())
                    .viewCnt(eNotice.getViewcnt())
                    .regDate(eNotice.getRegDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")))
                    .build();
            returnList.add(dNotice);
        }
        return returnList;
    }
}


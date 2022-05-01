package com.sjs.jsvill.repository.jimmy;

import com.sjs.jsvill.entity.jimmy.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

//    주석처리 해둠 - sean
//    에러 참고
//    Failed to create query for method public abstract com.sjs.jsvill.entity.jimmy.Notice com.sjs.jsvill.repository.jimmy.NoticeRepository.findByNotice_rowid(java.lang.Long)! No property notice found for type Notice!
//    Notice findByNotice_rowid(Long notice_rowid);
}

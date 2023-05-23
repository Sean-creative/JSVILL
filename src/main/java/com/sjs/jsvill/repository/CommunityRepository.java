package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    Page<Community> findAll(Pageable pageable);
    Page<Community> findByTitle(Pageable pageable, String searchTxt);
    Page<Community> findByCont(Pageable pageable, String searchTxt);
    Page<Community> findByWriter(Pageable pageable, String searchTxt);
//    Page<Community> findByTitleAndCont(Pageable pageable, String searchTxt);
//    Page<Community> findByTitleAndContAndWriter(Pageable pageable, String searchTxt);

    Community save(Community community);
    Community findByComRowid(Long comRowid);
/* Todo kjs 조회수 업데이트 쿼리문 안 쓰고 할 수 있는 방법 찾기 */
//    int updateByComRowid(Long comRowid,int readCnt);

//    Community save(Long comRowid);

}

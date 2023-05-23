package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    Page<Community> findAll(Pageable pageable);
    Page<Community> findByTitle(Pageable pageable, String searchTxt);
    Page<Community> findByCont(Pageable pageable, String searchTxt);
    Page<Community> findByWriter(Pageable pageable, String searchTxt);
//    Page<Community> findByTitleAndCont(Pageable pageable, String searchTxt);
//    Page<Community> findByTitleAndContAndWriter(Pageable pageable, String searchTxt);

    Community save(Community community);
    Community findByComRowid(Long comRowid);

    @Transactional
    void deleteByComRowid(Long comRowid);


}

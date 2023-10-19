package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByContract(Contract contract);

    @Transactional
    void deleteByFileKey(String fileKey);

    @Modifying
    @Query("UPDATE Photo p SET p.bookmark = :bookmark WHERE p.photoRowid = :photoRowid")
    int updateBookmarkByPhotoRowid(@Param("bookmark") Boolean bookmark, @Param("photoRowid") Long photoRowid);
}
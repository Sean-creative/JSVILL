package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Calendar;
import com.sjs.jsvill.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    List<Calendar> findAllByGroup(Group group);

    @Transactional
    @Modifying
    @Query("delete from Calendar c where c.bundleid=:bundleId")
    void deleteByBundleId(@Param("bundleId") Long bundleId);
}

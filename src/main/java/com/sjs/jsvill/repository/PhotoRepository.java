package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Photo;
import com.sjs.jsvill.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByUnit(Unit unit);


}
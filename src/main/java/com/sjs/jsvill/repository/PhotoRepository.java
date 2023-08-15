package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
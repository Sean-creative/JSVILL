package com.sjs.jsvill.service.community;

import com.sjs.jsvill.entity.Community;
import com.sjs.jsvill.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommunityService {
    Page<Community> findAll(Pageable pageable);

    /* Todo kjs dtoToEntity, entityToDto 더 자세히 이해필요 */
}

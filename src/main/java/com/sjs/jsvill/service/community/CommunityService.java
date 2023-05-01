package com.sjs.jsvill.service.community;

import com.sjs.jsvill.entity.Notice;

import java.util.List;

public interface CommunityService {
    List<Notice> findAll();

    /* Todo kjs dtoToEntity, entityToDto 더 자세히 이해필요 */
}

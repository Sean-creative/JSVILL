package com.sjs.jsvill.service.community;

import com.sjs.jsvill.entity.Community;
import com.sjs.jsvill.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service /* Todo kjs 이걸 넣고 빼고에 따라 어떤 차이가 있는지*/
@Log4j2
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;

//    @Transactional(readOnly = true)
    public Page<Community> findAll(Pageable pageable) {
        log.info("service");
        return communityRepository.findAll(pageable);
    }
}

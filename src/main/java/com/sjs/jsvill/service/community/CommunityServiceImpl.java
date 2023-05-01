package com.sjs.jsvill.service.community;

import com.sjs.jsvill.entity.Notice;
import com.sjs.jsvill.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service /* Todo kjs 이걸 넣고 빼고에 따라 어떤 차이가 있는지*/
@Log4j2
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {
    private final CommunityRepository communityRepository;

    @Override
    public List<Notice> findAll() {
        return communityRepository.findAll();
    }
}

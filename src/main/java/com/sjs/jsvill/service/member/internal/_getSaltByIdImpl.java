package com.sjs.jsvill.service.member.internal;

import com.sjs.jsvill.entity._Salt;
import com.sjs.jsvill.repository._SaltRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static org.reflections.Reflections.log;

@Service
@Log4j2
@RequiredArgsConstructor
public class _getSaltByIdImpl implements _getSaltById{

    private final _SaltRepository saltRepository;

    @Override
    public _Salt get(String id) {
        log.info("_getSaltByIdImpl !!!");

        return saltRepository.getSaltById(id);
    }
}

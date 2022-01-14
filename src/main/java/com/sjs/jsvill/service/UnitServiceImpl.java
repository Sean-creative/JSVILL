package com.sjs.jsvill.service;

import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository; //반드시 final로 선언

    @Override
    public Long register(UnitDTO dto) {
        log.info("DTO-------------" );
        log.info(dto);
        Unit unit = dtoToEntity(dto);
        unitRepository.save(unit);
        return unit.getUnit_rowid();
    }


//    @Override
//    public PageResultDTO<GroupDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
//        log.info(pageRequestDTO);
//
//        Function<Object[], GroupDTO> fn = (en -> entityToDTO((Group)en[0]));
//
//        Page<Object[]> result = repository.getGroupWithAll(pageRequestDTO.getPageable(Sort.by("group_rowid").descending()));
//        
//        return
//    }
}

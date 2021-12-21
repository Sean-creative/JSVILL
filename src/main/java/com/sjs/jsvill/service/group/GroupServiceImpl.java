package com.sjs.jsvill.service.group;

import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.dto.PageRequestDTO;
import com.sjs.jsvill.dto.PageResultDTO;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.repository.group.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repository; //반드시 final로 선언
    @Override
    public Long register(GroupDTO dto) {
        log.info("DTO-------------");
        log.info(dto);

        Group entity = dtoToEntity(dto);
        log.info(entity);

        repository.save(entity);
        return entity.getGroup_rowid();
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

package com.sjs.jsvill.service.community;

import com.sjs.jsvill.dto.CommunityDTO;
import com.sjs.jsvill.dto.GroupDTO;
import com.sjs.jsvill.entity.Community;
import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Notice;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommunityService {
    Page<Community> findAll(Pageable pageable, String searchKey, String searchTxt);
    Community save(CommunityDTO communityDTO);

    Community findByComRowid(Long comRowid);
    void deleteByComRowid(Long comRowid);

    default Community dtoToEntity(CommunityDTO communityDTO) {
        Community community = Community.builder()
                .comRowid(communityDTO.getComRowid())
                .type(communityDTO.getType())
                .title(communityDTO.getTitle())
                .cont(communityDTO.getCont())
                .readCnt(communityDTO.getReadCnt())
                .writer("관리자")
                .build();

        return community;
    }

    default CommunityDTO entityToDTO(Community community) {
        CommunityDTO communityDTO = CommunityDTO.builder()
                .comRowid(community.getComRowid())
                .type(community.getType())
                .title(community.getTitle())
                .cont(community.getCont())
                .readCnt(community.getReadCnt())
                .writer(community.getWriter())
                .build();
        return communityDTO;
    }

    /* Todo kjs dtoToEntity, entityToDto 더 자세히 이해필요 */
}

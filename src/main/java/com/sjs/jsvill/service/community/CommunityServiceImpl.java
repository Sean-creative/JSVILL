package com.sjs.jsvill.service.community;

import com.sjs.jsvill.dto.CommunityDTO;
import com.sjs.jsvill.entity.Community;
import com.sjs.jsvill.repository.CommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
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
    @Override /* Todo kjs 없어도 먹혔었다???? 왜???? */
    public Page<Community> findAll(Pageable pageable, String searchKey, String searchTxt) {
        log.info("service");
//        log.info("searchKey >>>>>>>>>>>>>>> " + searchKey);
//        log.info("searchTxt >>>>>>>>>>>>>>> " + searchTxt);

        if(searchKey.equals("t")) {
            log.info("searchKey >>>>>>>>>>>>>>> " + searchKey);
            return communityRepository.findByTitle(pageable,searchTxt);
        } else if(searchKey.equals("c")) {
            log.info("searchKey >>>>>>>>>>>>>>> " + searchKey);
            return communityRepository.findByCont(pageable,searchTxt);
        } else if(searchKey.equals("w")) {
            log.info("searchKey >>>>>>>>>>>>>>> " + searchKey);
            return communityRepository.findByWriter(pageable,searchTxt);
        }
//        else if(searchKey == "tc") {
//            return communityRepository.findByTitleAndCont(pageable,searchTxt);
//        }
//        else if(searchKey == "tcw") {
//            return communityRepository.findByTitleAndContAndWriter(pageable,searchTxt);
//        }
        return communityRepository.findAll(pageable);
    }

    @Override
    public Community save(CommunityDTO communityDTO) {
        Community community = dtoToEntity(communityDTO);

        log.info("community >>>>>>>>>>>>> " + community);
        return communityRepository.save(community);
    }

    @Override
    public Community findByComRowid(Long comRowid) {
        Community community;
        community = communityRepository.findByComRowid(comRowid);
        CommunityDTO communityDTO = entityToDTO(community);
        int readCnt = communityDTO.getReadCnt() + 1;
        communityDTO.setReadCnt(readCnt);
        community = dtoToEntity(communityDTO);
        communityRepository.save(community);

        return community;
    }

    @Override
    public void deleteByComRowid(Long comRowid) {
        communityRepository.deleteByComRowid(comRowid);
    }
}

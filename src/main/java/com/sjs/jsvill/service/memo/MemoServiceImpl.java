package com.sjs.jsvill.service.memo;

import com.sjs.jsvill.dto.PhotoDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Memo;
import com.sjs.jsvill.entity.Photo;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.repository.ContractRepository;
import com.sjs.jsvill.repository.MemoRepository;
import com.sjs.jsvill.repository.PhotoRepository;
import com.sjs.jsvill.util.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService {

    private final MemoRepository memoRepository;

    @SneakyThrows
    @Override
    public Memo register(Memo memo) {
        return memoRepository.save(memo);
    }

    @Override
    public List<Memo> getList(Long unitRowid) {
        Unit unit = Unit.builder().unit_rowid(unitRowid).build();
        return memoRepository.findByUnit(unit);
    }
}
